package com.elva.conn.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.elva.util.CommonUtil;

public class TestSSLServerSocket {
	public static void sslContext() throws Exception{
		String keystoreFile = 
				TestSSLServerSocket.class.getResource("server.keystore")
				.getFile();
		File file = new File(keystoreFile);
		X509TrustManager trustManager = new X509TrustManager(){
			/**
			 * 执行服务器的安全策略
			 * 一种增强服务器安全状态的方法就是要求客户端用数字证书来证明身份，
			 * 只接受由一个特定的CA（如VeriSign CA）发布的证书
			 * ----sslsocket.setNeetClientAuth(true)
			 * ----在从客户端输入流读入数据时，会调用该方法
			 */
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				System.out.println("------------checkClientTrusted----------");
				System.out.println(chain.length);
				System.out.println(authType);
				X509Certificate cert = chain[0];
				System.out.println(cert.getSerialNumber());
				System.out.println(cert.getIssuerDN().getName());
				System.out.println("------------checkClientTrusted done------");
			}
			/**
			 * 服务端一般不会调用这个方法
			 */
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
				System.out.println("------------checkServerTrusted----------");
				System.out.println(chain.length);
				System.out.println(authType);
				X509Certificate cert = chain[0];
				System.out.println(cert.getSerialNumber());
				System.out.println(cert.getIssuerDN().getName());
				System.out.println("------------checkServerTrusted done------");
			}
			public X509Certificate[] getAcceptedIssuers() {
				System.out.println("333333333333333");
				ArrayList<X509Certificate> list = new ArrayList<X509Certificate>();
				return list.toArray(new X509Certificate[0]);
			}
		};
		KeyStore ks = KeyStore.getInstance("JKS");
		FileInputStream fis = new FileInputStream(file);
		ks.load(fis, "123456".toCharArray());
		fis.close();
		
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, "123456".toCharArray());
		
		SSLContext context = SSLContext.getInstance("SSL");
		context.init(
				kmf.getKeyManagers(),
				//TrustManager可以new对象，也可以使用TrustManagerFactory从keystore中加载
				new TrustManager[]{trustManager}, 
				new SecureRandom());
		
		SSLServerSocketFactory serverFactory = context.getServerSocketFactory();
		SSLServerSocket server 
			= (SSLServerSocket)serverFactory.createServerSocket(10002);
		
		SSLSocket client = (SSLSocket)server.accept();
		//需要验证客户端证书
		client.setNeedClientAuth(true);
//		System.out.println(client instanceof SSLSocket);
		System.out.println(client.getRemoteSocketAddress());
		
		OutputStream output = client.getOutputStream();
		InputStream input = client.getInputStream();
		
		String resp = CommonUtil.readStringFromInput(input);
		
		System.out.println("received from client:"+resp);
		output.write("received success".getBytes());
		output.flush();
		
		output.close();
		input.close();
		
		client.close();
		server.close();
		
	}
	
	public static void main(String[] args) throws Exception {
		sslContext();
	}

}

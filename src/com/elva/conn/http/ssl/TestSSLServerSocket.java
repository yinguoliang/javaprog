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
			 * ִ�з������İ�ȫ����
			 * һ����ǿ��������ȫ״̬�ķ�������Ҫ��ͻ���������֤����֤����ݣ�
			 * ֻ������һ���ض���CA����VeriSign CA��������֤��
			 * ----sslsocket.setNeetClientAuth(true)
			 * ----�ڴӿͻ�����������������ʱ������ø÷���
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
			 * �����һ�㲻������������
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
				//TrustManager����new����Ҳ����ʹ��TrustManagerFactory��keystore�м���
				new TrustManager[]{trustManager}, 
				new SecureRandom());
		
		SSLServerSocketFactory serverFactory = context.getServerSocketFactory();
		SSLServerSocket server 
			= (SSLServerSocket)serverFactory.createServerSocket(10002);
		
		SSLSocket client = (SSLSocket)server.accept();
		//��Ҫ��֤�ͻ���֤��
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

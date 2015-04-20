package com.elva.conn.http.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TestSSLClientSocket {
	public static void main(String[] args) throws Exception{
		String keystoreFile = 
				TestSSLServerSocket.class.getResource("client.keystore")
				.getFile();
		File file = new File(keystoreFile);
		KeyStore ks = KeyStore.getInstance("JKS");
		FileInputStream fis = new FileInputStream(file);
		ks.load(fis, "123456".toCharArray());
		fis.close();
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(ks, "123456".toCharArray());
		SSLContext context = SSLContext.getInstance("SSL");
		context.init(
				null,//kmf.getKeyManagers(), 
				new TrustManager[]{new X509TrustManager(){
					/**
					 * 执行服务器的安全策略
					 * 一种增强服务器安全状态的方法就是要求客户端用数字证书来证明身份，
					 * 只接受由一个特定的CA（如VeriSign CA）发布的证书
					 */
					public void checkClientTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						System.out.println("************checkClientTrusted***********");
						System.out.println(chain.length);
						System.out.println(authType);
						X509Certificate cert = chain[0];
						System.out.println(cert.getSerialNumber());
						System.out.println(cert.getIssuerDN().getName());
						System.out.println("************checkClientTrusted done***********");
					}
					/**
					 * 执行客户端的安全策略,客户端确认服务器的真实性
					 * 客户端通过有效的证书来信任服务器
					 * ------在从服务端读取数据时，会调用该方法
					 */
					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						System.out.println("************checkServerTrusted***********");
						System.out.println(chain.length);
						System.out.println(authType);
						X509Certificate cert = chain[0];
						System.out.println(cert.getSerialNumber());
						System.out.println(cert.getIssuerDN().getName());
						//如果我们拿到了公钥的签名证书，即CA证书，并从中读取了publicKey,则可使用如下方法来验证证书
						//cert.verify(publickey);
						System.out.println("************checkServerTrusted done***********");
						//throw new RuntimeException("untrusted certificate");
					}
					public X509Certificate[] getAcceptedIssuers() {
						System.out.println("cccccccccccccccccc");
						return null;
					}}}, 
				null);
		SSLSocketFactory factory = context.getSocketFactory();
		SSLSocket socket = (SSLSocket)factory.createSocket(
				"192.168.1.104",10002);
		System.out.println("ok"); 
		
		OutputStream output = socket.getOutputStream();
		InputStream input = socket.getInputStream();
		
		output.write("hello server".getBytes());
		System.out.println("client sent message success");
		output.flush();

		byte[] buffer = new byte[1024];
		System.out.println("read from server......");
		int len = input.read(buffer);

		System.out.println("read from server done.");
		System.out.println(new String(buffer,0,len));
		
	}

}

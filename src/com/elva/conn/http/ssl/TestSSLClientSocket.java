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
					 * ִ�з������İ�ȫ����
					 * һ����ǿ��������ȫ״̬�ķ�������Ҫ��ͻ���������֤����֤����ݣ�
					 * ֻ������һ���ض���CA����VeriSign CA��������֤��
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
					 * ִ�пͻ��˵İ�ȫ����,�ͻ���ȷ�Ϸ���������ʵ��
					 * �ͻ���ͨ����Ч��֤�������η�����
					 * ------�ڴӷ���˶�ȡ����ʱ������ø÷���
					 */
					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						System.out.println("************checkServerTrusted***********");
						System.out.println(chain.length);
						System.out.println(authType);
						X509Certificate cert = chain[0];
						System.out.println(cert.getSerialNumber());
						System.out.println(cert.getIssuerDN().getName());
						//��������õ��˹�Կ��ǩ��֤�飬��CA֤�飬�����ж�ȡ��publicKey,���ʹ�����·�������֤֤��
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

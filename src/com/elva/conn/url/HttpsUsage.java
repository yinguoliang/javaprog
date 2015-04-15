package com.elva.conn.url;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
/**
 * httpsʹ��SSL/TLS�ԻỰ��Ϣ���м��ܣ���֤ͨ����·�İ�ȫ
 * @author lenovo
 *
 */
public class HttpsUsage {
	
	public static void main(String[] args) throws Exception{
		URL url = new URL("https://www.baidu.com");
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		/*
		 * Https������Ҫһ��SSLSocketFactory����
		 */
		conn.setSSLSocketFactory(getSocketFactory());
		/*
		 * ������֤
		 */
		//httpclient4.3�ж����AllowAllHostnameVerifier
//		conn.setHostnameVerifier(new AllowAllHostnameVerifier());
		conn.setHostnameVerifier(new HostnameVerifier(){
			public boolean verify(String arg0, SSLSession arg1) {
				System.out.println(arg0+","+arg1.getPeerHost());
				return false;
			}
		});
		conn.connect();
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int v = 0;
		while((v=in.read())>-1)
			bos.write(v);
		in.close();
		bos.close();
		System.out.println(bos.toString());
	}
	
	public static SSLSocketFactory getSocketFactory() throws Exception{
		SSLContext sslContext = SSLContext.getInstance("SSL");//SSL��TLS
		sslContext.init(
				//KeyManagerԶ�̻������ε���Կ�����Դ�֤���м��أ���Կ��
				null,
				//TrustManager����������
				new MyX509TrustManager[]{new MyX509TrustManager()},
				null//SecureRandom SSL�Ự�л�ʹ�������
			);
		return sslContext.getSocketFactory();
	}
	

}
/**
 * X509��һ��������Կ�洢��֤���ʽ��һ��X509Certificate����һ����Կ֤��
 * @author lenovo
 *
 */
class MyX509TrustManager implements X509TrustManager{
	/**
	 * �������֤�ͻ��˵�����
	 */
	public void checkClientTrusted(X509Certificate[] arg0,
			String arg1) throws CertificateException {
		
	}
	/**
	 * �ͻ�����֤���������
	 */
	public void checkServerTrusted(X509Certificate[] arg0,
			String arg1) throws CertificateException {
		System.out.println("cert: " + arg0[0].toString() + ", authType: " + arg1);
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	
}

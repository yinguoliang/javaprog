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
 * https使用SSL/TLS对会话消息进行加密，保证通信链路的安全
 * @author lenovo
 *
 */
public class HttpsUsage {
	
	public static void main(String[] args) throws Exception{
		URL url = new URL("https://www.baidu.com");
		HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
		/*
		 * Https可能需要一个SSLSocketFactory对象
		 */
		conn.setSSLSocketFactory(getSocketFactory());
		/*
		 * 主机验证
		 */
		//httpclient4.3中定义的AllowAllHostnameVerifier
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
		SSLContext sslContext = SSLContext.getInstance("SSL");//SSL或TLS
		sslContext.init(
				//KeyManager远程机器信任的秘钥，可以从证书中加载（公钥）
				null,
				//TrustManager，本地信任
				new MyX509TrustManager[]{new MyX509TrustManager()},
				null//SecureRandom SSL会话中会使用随机数
			);
		return sslContext.getSocketFactory();
	}
	

}
/**
 * X509是一种描述公钥存储的证书格式，一个X509Certificate代表一个公钥证书
 * @author lenovo
 *
 */
class MyX509TrustManager implements X509TrustManager{
	/**
	 * 服务端验证客户端的请求
	 */
	public void checkClientTrusted(X509Certificate[] arg0,
			String arg1) throws CertificateException {
		
	}
	/**
	 * 客户端验证服务端请求
	 */
	public void checkServerTrusted(X509Certificate[] arg0,
			String arg1) throws CertificateException {
		System.out.println("cert: " + arg0[0].toString() + ", authType: " + arg1);
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	
}

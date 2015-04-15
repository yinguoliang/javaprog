package com.elva.conn.url;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicUsage {
	public static void basicUseage1() throws Exception{
		/*
		 * URL�еĲ���������Content-Type������ã�������������
		 * ����ͨ��request.getParameter��ȡ
		 */
		URL url = new URL("http://localhost:8080/weichat/wechat.do?from=ygl");
		//��httpЭ�飬����HttpURLConnection
		//�����httpsЭ�飬�򷵻�HttpsURLConnection
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		System.out.println(conn);
		//д����
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		/*
		 * ��Content-Type=application/x-www-form-urlencoded:
		 *   ͨ��������write�����ݣ��������������ڷ��񵥿���ͬrequest.getParameter()��ȡ
		 */
//		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		/*
		 * ��Content-TypeΪ
		 * 		application/x-java-serialized-object
		 *  	text/plain
		 *  	text/html
		 *      ��������������
		 *   ͨ��OutputStream write��ȥ�����ݣ��������ֽ������ڷ���ͨ��request.getInputStream��ȡ
		 */
		conn.setRequestProperty("Content-Type","application/x-java-serialized-object");
		
		conn.getOutputStream().write("a=1&b=2".getBytes());
		conn.getOutputStream().close();
		
		conn.connect();
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		InputStream is = conn.getInputStream();
		int v = 0;
		while((v=is.read())>=0){
			bos.write(v);
		}
		is.close();
		bos.close();
		System.out.println("**************response*************");
		System.out.println(bos.toString());
	}
	public static void main(String[] args) throws Exception {
		basicUseage1();
	}
}

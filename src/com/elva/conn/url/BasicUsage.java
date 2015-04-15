package com.elva.conn.url;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicUsage {
	public static void basicUseage1() throws Exception{
		/*
		 * URL中的参数，无论Content-Type如何设置，都被当做参数
		 * 可以通过request.getParameter获取
		 */
		URL url = new URL("http://localhost:8080/weichat/wechat.do?from=ygl");
		//对http协议，返回HttpURLConnection
		//如果是https协议，则返回HttpsURLConnection
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		System.out.println(conn);
		//写参数
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		/*
		 * 对Content-Type=application/x-www-form-urlencoded:
		 *   通过输入流write的内容，被当做参数，在服务单可以同request.getParameter()获取
		 */
//		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
		/*
		 * 对Content-Type为
		 * 		application/x-java-serialized-object
		 *  	text/plain
		 *  	text/html
		 *      。。。。。。。
		 *   通过OutputStream write进去的内容，被当做字节流，在服务单通过request.getInputStream读取
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

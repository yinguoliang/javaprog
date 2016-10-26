package com.elva;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hello {
	public void sayHello(){
		System.out.println("Hello......every one");
	}
	public static void main(String[] args) {
//		System.out.println("http://www.zhongan.com".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("https://www.zhongan.com".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("http://www.zhongan.com:8080".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("https://www.zhongan.com:8080".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("http://www.zhongan.com/hello/index.vm".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("https://www.zhongan.comTTT/html.htm?v=2332".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("ddshttp://www.zhongan.com/keeeee".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("<a href=http://www.zhongan.com>dddd</a>".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
//		System.out.println("httphttp://www.zhongan.com".replaceAll("((http|https)://(.*).zhongan.com)", "mydomain"));
		System.out.println("https://www.zhongan.com aaaa https://www.zhongan.com bbbb https://www.zhongan.com "
		        .replaceAll("((http|https)://(.*?)\\.zhongan\\.com)", "mydomain"));
		
		Pattern p = Pattern.compile("(https?://.*?\\.zhongan\\.com)");
		
		
//		System.out.println(m.replaceAll("xxxxx"));
		String result = "https://www.zhongan.com aaaa http://www.zhongan.com bbbb https://www.zhongan.com ";
		Matcher m = p.matcher(result);
		while(m.find()){
		    result = result.replace(m.group(), "www.mydomain.com");
		}
		System.out.println(result);
		    
	}

}

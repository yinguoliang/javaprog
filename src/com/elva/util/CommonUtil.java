package com.elva.util;

import java.io.InputStream;

public class CommonUtil {
	public static String readStringFromInput(InputStream input)
		throws Exception {
		byte[] bytes = new byte[1024];
		int len = input.read(bytes);
		return new String(bytes,0,len);
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		
//		byte[] buf = new byte[1024];
//		int len = 0;
//		while((len=input.read(buf))>0){
//			bos.write(buf, 0, len);
//		}
//		bos.close();
//		
//		return bos.toString();
	}
}

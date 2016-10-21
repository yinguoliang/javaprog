package com.elva.guava.file;

import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.io.Files;

public class FileTest {
	public static void main(String args[]) throws Exception{
		List<String> lines = 
				Files.readLines(new File("d:/eula.2052.txt"), Charset.forName("Unicode"));
		for(String line : lines){
			System.out.println(line);
		}
		
	}
}

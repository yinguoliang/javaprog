package com.elva.util;

public class U {
	public static void sleep(long t){
		try {
			Thread.sleep(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static String currentThreadName(){
	    return Thread.currentThread().getName();
	}
	public static void print(Object obj){
		System.out.println(currentThreadName()+":"+obj);
	}
}

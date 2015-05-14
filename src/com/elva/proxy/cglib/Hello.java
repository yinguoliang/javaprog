package com.elva.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;


public class Hello {
	public void hello(){
		System.out.println("cglib hello");
	}
	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"f:/temp/");
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(new TimerInterceptor());
		enhancer.setSuperclass(Hello.class);
		Hello hello = (Hello)enhancer.create();
		hello.hello();
	}

}

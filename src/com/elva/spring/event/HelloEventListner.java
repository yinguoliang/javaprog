package com.elva.spring.event;

import org.springframework.context.ApplicationListener;

public class HelloEventListner implements ApplicationListener<HelloEvent>{
	public void onApplicationEvent(HelloEvent event) {
		System.out.println("event occur:"+event.getSource());
	}
}

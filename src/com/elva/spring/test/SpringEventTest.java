package com.elva.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.elva.spring.event.HelloEvent;
@SuppressWarnings("resource")
public class SpringEventTest {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		context.publishEvent(new HelloEvent("hello"));
	}
}

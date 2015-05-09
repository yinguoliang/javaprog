package com.elva.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.elva.spring.service.HelloService;
@SuppressWarnings("resource")
public class SpringMainTest {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		HelloService service = (HelloService)context.getBean("helloService");
		service.hello();
	}
}

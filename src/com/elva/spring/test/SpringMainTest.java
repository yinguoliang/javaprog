package com.elva.spring.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.elva.spring.service.BusinessService;
@SuppressWarnings("resource")
public class SpringMainTest {
	public static void main(String[] args) {
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		BusinessService service = (BusinessService)context.getBean("businessService");
		List<String> list = service.insertAndQuery(Arrays.asList("AAAA"));
		for(String s : list){
			System.out.println(s);
		}
	}
}

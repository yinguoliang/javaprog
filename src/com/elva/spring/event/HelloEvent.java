package com.elva.spring.event;

import org.springframework.context.ApplicationEvent;

public class HelloEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4794557678897291814L;
	public HelloEvent(Object source) {
		super(source);
	}
}

package com.elva.spring.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PrintcallInterceptor implements MethodInterceptor{
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("**intercept** call:"+invocation.getMethod().getName());
		return invocation.proceed();
	}
}

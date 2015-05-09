package com.elva.spring.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeInterceptor implements MethodInterceptor{
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long start = System.currentTimeMillis();
		Object ret = invocation.proceed();
		System.out.println("**intercept** execute "+invocation.getMethod().getName()
				+" cost:"+(System.currentTimeMillis()-start)+" ms.");
		return ret;
	}


}

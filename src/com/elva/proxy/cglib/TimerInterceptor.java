package com.elva.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


public class TimerInterceptor implements MethodInterceptor{
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		long t1 = System.currentTimeMillis();
		Object ret = arg3.invokeSuper(arg0, arg2);
		long t2 = System.currentTimeMillis();
		System.out.println("execute "+arg1.getName()+" cost "+(t2-t1)+"ms");
		return ret;
	}

}

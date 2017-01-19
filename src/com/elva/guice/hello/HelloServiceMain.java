package com.elva.guice.hello;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class HelloServiceMain {
    public static void main(String args[]) throws Exception {
        Injector injector = Guice.createInjector(new HelloServiceModule());
        HelloService hello = injector.getInstance(HelloService.class);
        String msg = hello.hello("ygl");
        System.out.println(msg);
    }
}

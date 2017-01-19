package com.elva.guice.hello;

import java.util.Map;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class HelloServiceMain {
    @Inject
    HelloService helloService;
    public void test(){
        String msg = helloService.hello("ZZZZ");
        System.out.println(msg);
    }
    public static void baseTest(){
        Injector injector = Guice.createInjector(new HelloServiceModule());
        Map<?,?> bindings = injector.getBindings();
        for(Map.Entry<?,?> e : bindings.entrySet()){
            System.out.println(e.getKey()+":"+e.getValue());
        }
        HelloService hello = injector.getInstance(HelloService.class);
        String msg = hello.hello("ygl");
        System.out.println(msg);
        //--------------------------------
        Injector injector2 = Guice.createInjector();
        HelloServiceMain m = injector2.getInstance(HelloServiceMain.class);
        m.test();
    }
    public static void main(String args[]) throws Exception {
        baseTest();
    }
}

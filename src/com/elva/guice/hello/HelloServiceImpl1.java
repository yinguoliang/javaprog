package com.elva.guice.hello;
public class HelloServiceImpl1 implements HelloService{
    public String hello(String str) {
        return "Hello 1 "+str;
    }
}

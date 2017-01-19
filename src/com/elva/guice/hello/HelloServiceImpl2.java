package com.elva.guice.hello;
public class HelloServiceImpl2 implements HelloService{
    @Override
    public String hello(String str) {
        return "Hello 2 "+str;
    }

}

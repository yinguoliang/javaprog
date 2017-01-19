package com.elva.guice.hello;

import com.google.inject.ImplementedBy;

@ImplementedBy(HelloServiceImpl2.class)
public interface HelloService {
    public String hello(String str);
}

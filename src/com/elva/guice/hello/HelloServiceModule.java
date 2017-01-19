package com.elva.guice.hello;

import com.google.inject.Binder;
import com.google.inject.Module;

public class HelloServiceModule implements Module{
    @Override
    public void configure(Binder binder) {
        binder.bind(HelloService.class).to(HelloServiceImpl1.class);
    }
}

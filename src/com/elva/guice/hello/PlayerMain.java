package com.elva.guice.hello;

import javax.inject.Named;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class PlayerMain {
    
    public void test(){
        Injector injector = Guice.createInjector(new PlayerModule());
        @Named("Good") Player good = injector.getInstance(Player.class);
        @Named("Bad") Player bad = injector.getInstance(Player.class);
        System.out.println(good.play("yestoday"));
        System.out.println(bad.play("yestoday"));
    }
    
    public void test2(){
        Injector injector = Guice.createInjector(new Module(){
            public void configure(Binder binder) {
                binder.bind(Player.class).toProvider(PlayerProvider.class);
            }});
        Player p = injector.getInstance(Player.class);
        System.out.println(p.play("today"));
    }
    
    public static void main(String[] args) {
        new PlayerMain().test();
    }
}

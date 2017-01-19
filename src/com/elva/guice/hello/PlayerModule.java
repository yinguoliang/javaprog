package com.elva.guice.hello;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class PlayerModule implements Module{
    public void configure(Binder binder) {
        binder.bind(Player.class).to(PlayerImpl1.class);
        binder.bind(Player.class).annotatedWith(Names.named("Bad")).to(PlayerImpl2.class);
    }
}

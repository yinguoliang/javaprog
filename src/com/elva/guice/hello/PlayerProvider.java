package com.elva.guice.hello;

import com.google.inject.Provider;

public class PlayerProvider implements Provider<Player>{
    @Override
    public Player get() {
        return new PlayerImpl1();
    }
}

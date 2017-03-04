package com.elva.jdk8;

import java.util.stream.Stream;

public class StreamTest {
    public static void test1() throws Exception{
        Stream<String> stream = Stream.of("A","C","D","e","e","e","E");
        stream.filter(s->s.length()<10)
        .map(s->s.toLowerCase())
        .distinct()
        .forEach(System.out::println);
        ;
    }
    public static void main(String args[]) throws Exception{
        test1();
    }
}

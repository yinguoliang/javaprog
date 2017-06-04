package com.elva.regex;

public class RegextTest {
    public static void main(String args[]) {
//        System.out.println("abcd1231eee3333dddd".matches("^[a-z]+\\d+[a-z]+\\d+[a-z]+$"));
        boolean b = Pattern.matches("to(night|knight|niii)", "tonight");
        System.out.println(b);
    }
}

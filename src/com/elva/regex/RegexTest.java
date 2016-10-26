package com.elva.regex;

import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String args[]) throws Exception{
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", ""));
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", "ÖÐ»ª123"));
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", "123"));
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", "abc"));
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", "abc123abc"));
        System.out.println(Pattern.matches("^[A-Za-z0-9]+$", "abc12#3abc"));
    }
}

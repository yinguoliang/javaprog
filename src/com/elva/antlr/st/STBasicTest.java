package com.elva.antlr.st;

import org.stringtemplate.v4.ST;

public class STBasicTest {
    /**
     * @see https://github.com/antlr/stringtemplate4/blob/master/doc/java.md
     */
    public static void main(String args[]) throws Exception{
        ST hello = new ST("Hello <name>");
        hello.add("name", "YGL");
        System.out.println(hello.render());
    }
}

package com.elva.antlr.basic.exam1;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.BaseTree;

public class Exam1Main {
    //java -cp ../antlr-3.2.jar;../antlr-runtime-3.2.jar;../stringtemplate-3.2.jar;../antlr-2.7.7.jar org.antlr.Tool Expr.g
    public static void main(String[] args) throws Exception {
        String[] testStr = {
                "2",
                "a + b + 3",
                "(a - b) + 3",
                "a + (b * 3) * 123 * (ab*cd)",                                   
        };
        for (String s : testStr) {
            System.out.println("Input expr: " + s);
            run(s);
        }
    }
    
    public static void run(String expr) throws Exception {
        ANTLRStringStream in = new ANTLRStringStream(expr);
        //词法分析器
        HelloLexer lexer = new HelloLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器 
        HelloParser parser = new HelloParser(tokens);
        HelloParser.prog_return ret=parser.prog();
        System.out.println(((BaseTree)ret.getTree()).toStringTree());
        print("Hello keyboard");
    }
    
    public static void print(Object obj){
    	System.out.println("msg"+obj);
    }
}

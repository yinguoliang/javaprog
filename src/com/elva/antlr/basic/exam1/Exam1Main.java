package com.elva.antlr.basic.exam1;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

public class Exam1Main {
//    public static void main(String args[]) throws Exception{
////        File f = new File("d:/antlr/first/");
////        f.mkdirs();
////        Tool.main(new String[]{"-verbose","-debug","-print","-profile","-fo d:/antlr/","d:/antlr/first/Expr.g"});
//        
//        
//    }

    //java -cp ../antlr-3.2.jar;../antlr-runtime-3.2.jar;../stringtemplate-3.2.jar;../antlr-2.7.7.jar org.antlr.Tool Expr.g
    public static void main(String[] args) throws Exception {
        String[] testStr = { 
                "2",
                "a + b + 3",
                "(a - b) + 3",
                "a + (b * 3)",                                   
        };
        for (String s : testStr) {
            System.out.println("Input expr: " + s);
            run(s);
        }
    }
    
    public static void run(String expr) throws Exception {
        ANTLRStringStream in = new ANTLRStringStream(expr);
        //词法分析器
        ExprLexer lexer = new ExprLexer(in);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        //语法分析器 
        ExprParser parser = new ExprParser(tokens);

        parser.prog();      
    }
}

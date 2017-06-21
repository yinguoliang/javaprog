package com.elva.jdk8;

import java.util.concurrent.Callable;

public class LambdaTest {
    static interface Add{
        int add(int a,int b);
    }
    public static void test1() throws Exception{
        //lambda表达式实现上就是一个匿名表达式
        //函数式接口：只有一个抽象函数的接口，任意lambda表达式都可以转为函数式接口
        Runnable r = ()->System.out.println("hello lambda");
        r.run();
        Callable<String> c = ()->"this is callable";
        System.out.println(c.call());
        Add add = (x,y)-> x+y;
        System.out.println("5+5="+add.add(5,5));
    }
    public static void main(String args[]) throws Exception{
        test1();
    }
}

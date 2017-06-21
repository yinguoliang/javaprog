package com.elva.jdk8;

import java.util.concurrent.Callable;

public class LambdaTest {
    static interface Add{
        int add(int a,int b);
    }
    public static void test1() throws Exception{
        //lambda���ʽʵ���Ͼ���һ���������ʽ
        //����ʽ�ӿڣ�ֻ��һ���������Ľӿڣ�����lambda���ʽ������תΪ����ʽ�ӿ�
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

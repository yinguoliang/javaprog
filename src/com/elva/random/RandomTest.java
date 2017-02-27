package com.elva.random;

import java.util.Random;

public class RandomTest {
    public static void test1(){
        /*
         * 通过固定的种子，后面给出的随机序列是固定的
         * 也即：Random是伪随机的
         */
        Random random = new Random(139300);
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
    }
    public static void test2(){
        /*
         * 种子碰撞
         * 通过test1,我们知道给定种子，next后面的数的序列也是固定的
         * 因此，如果种子泄露或者通过穷举，是可以预测到随机序列的
         */
        //穷举，上面随机序列为：
        //-1136030948
        //-697039044
        //-1165138230
        //-242229055
        
        for(int i=1;i<Integer.MAX_VALUE;i++){
            Random r = new Random(i);
            if(r.nextInt()==-1136030948
            && r.nextInt()==-697039044
            && r.nextInt()==-1165138230
            && r.nextInt()==-242229055)
            {
                System.out.println("碰撞成功,种子="+i);
                break;
            }
        }
    }
    public static void main(String args[]) {
        test2();
    }
}

package com.elva.random;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

public class RandomTest {
    public static void test1(){
        /*
         * ͨ���̶������ӣ������������������ǹ̶���
         * Ҳ����Random��α�����
         */
        Random random = new Random(139300);
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        System.out.println(random.nextInt());
        
        //��������ͨ������������Ϊ���ӵ��������������ظ�
        System.out.println(new Random(new Date().getTime()).nextInt());
        System.out.println(new Random(new Date().getTime()).nextInt());
        System.out.println(new Random(new Date().getTime()).nextInt());
        System.out.println(new Random(new Date().getTime()).nextInt());
        System.out.println(new Random(new Date().getTime()).nextInt());
        
    }
    public static void test2(){
        /*
         * ������ײ
         * ͨ��test1,����֪���������ӣ�next�������������Ҳ�ǹ̶���
         * ��ˣ��������й¶����ͨ����٣��ǿ���Ԥ�⵽������е�
         */
        //��٣������������Ϊ��
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
                System.out.println("��ײ�ɹ�,����="+i);
                break;
            }
        }
    }
    public static void test3(){
        //SecureRandom
        try {
            SecureRandom sr=SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed(1234332);
            System.out.println(sr.nextInt(1000000));
            System.out.println(sr.nextInt(1000000));
            System.out.println(sr.nextInt(1000000));
            System.out.println(sr.nextInt(1000000));
            System.out.println(sr.nextInt(1000000));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]) {
        test1();
    }
}

package com.elva.random;

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
    public static void main(String args[]) {
        test2();
    }
}

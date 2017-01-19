package com.elva.innerclass;
public class InnerClassTest {
    private int counter=0;
    public void incr(){
        counter++;
    }
    public int getCounter(){
        return counter;
    }
    class Inner{
        public void doIncr(){
            incr();
        }
    }
    
    public static void main(){
        InnerClassTest t = new InnerClassTest();
        /**
         * **********����Ԥ��***************
         * ������Կ������ڲ���ʵ�����������ⲿ��ʵ����
         * ���ǲ���ֱ��new Outer.Inner()
         * ��ֻ��ʹ���ⲿ���ʵ��outer.new Inner()
         * �������ڲ����ʵ������ֱ�ӷ����ⲿ���ʵ������
         * �ܶ����඼ʹ�����ڲ����������ԣ����һЩ���ܡ�
         * ����concurrent�������shecudeAtFixedRate()�Ͷ�����һ���ڲ�Task������װ�ⲿ�����Runnable
         * Task���ڲ�����ֱ�ӵ����ⲿ�ķ�������ִ�н��������¼�һ�������ȥ
         */
        InnerClassTest.Inner i = t.new Inner();
        i.doIncr();
    }
}

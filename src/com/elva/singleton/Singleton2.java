package com.elva.singleton;
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();
    /**
     * �������أ�Ч�ʿ��ܻ��е����⣬��Ȼ����������ģ�����ʹ��
     * @return
     */
    public static Singleton2 getInstance(){
        return instance;
    }
}

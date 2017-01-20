package com.elva.singleton;
public class Singleton3 {
    private static class Singleton3Factory{
        private static Singleton3 instance = new Singleton3();
    }
    /**
     * ����д����������ļ��ػ��ƣ�ֻ����ʵ�ʱ�ʹ�õ�ʱ��Żᱻ����
     * ���ֻ�е�����getInstance()ʱ���ڲ���Żᱻ���أ���ֻ������ʱ�������Żᱻ��ʼ��
     * ��Ϊ��ֻ�ܼ���һ�Σ�����Ҳ�����ж��̲߳���ȫ������
     * 
     * @return
     */
    public static Singleton3 getInstance(){
        return Singleton3Factory.instance;
    }
}

package com.elva.singleton;
public class Singleton1 {
    private static Singleton1 instance;
    /**
     * �ӳټ���
     * ���Ƕ��̳߳�ʼ����ʱ�򣬿��ܻ�������ʵ��
     * ���ң����instance������ʵ��״̬��ʱ�򣬻������һ�£�����ȫ��
     * �����ڶ��߳��²���ȫ
     * @return
     */
    public Singleton1 getIntance(){
        if(instance==null){
            instance=new Singleton1();
        }
        return instance;
    }
}

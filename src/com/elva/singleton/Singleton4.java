package com.elva.singleton;
public class Singleton4 {
    /**
     * ��Ӧ����volatile��
     * @see http://blog.csdn.net/li295214001/article/details/48135939
     */
    private volatile static Singleton4 instance;
    /**
     * ˫�ؼ������
     * 
     * @return
     */
    public static Singleton4 getInstance(){
        if(instance==null){
            synchronized(Singleton4.class){//��ֹ���̲߳�������
                if(instance==null){//���߳���ǰ���Ŷӣ�����Ľ���ҲҪ���ж�һ��
                    instance=new Singleton4();
                }
            }
        }
        return instance;
    }
}

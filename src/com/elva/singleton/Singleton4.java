package com.elva.singleton;
public class Singleton4 {
    /**
     * 类应该是volatile的
     * @see http://blog.csdn.net/li295214001/article/details/48135939
     */
    private volatile static Singleton4 instance;
    /**
     * 双重检查锁定
     * 
     * @return
     */
    public static Singleton4 getInstance(){
        if(instance==null){
            synchronized(Singleton4.class){//防止多线程并发进来
                if(instance==null){//多线程在前面排队，后面的进来也要再判断一下
                    instance=new Singleton4();
                }
            }
        }
        return instance;
    }
}

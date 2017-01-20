package com.elva.singleton;
public class Singleton1 {
    private static Singleton1 instance;
    /**
     * 延迟加载
     * 但是多线程初始化的时候，可能会产生多个实例
     * 并且，如果instance对象有实例状态的时候，会产生不一致（不安全）
     * 即：在多线程下不安全
     * @return
     */
    public Singleton1 getIntance(){
        if(instance==null){
            instance=new Singleton1();
        }
        return instance;
    }
}

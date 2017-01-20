package com.elva.singleton;
public class Singleton3 {
    private static class Singleton3Factory{
        private static Singleton3 instance = new Singleton3();
    }
    /**
     * 这种写法利用了类的加载机制：只有在实际被使用的时候才会被加载
     * 这里，只有调用了getInstance()时，内部类才会被加载，而只有在这时，单例才会被初始化
     * 因为类只能加载一次，所以也不会有多线程不安全的问题
     * 
     * @return
     */
    public static Singleton3 getInstance(){
        return Singleton3Factory.instance;
    }
}

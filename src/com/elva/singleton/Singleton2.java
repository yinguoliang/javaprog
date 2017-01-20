package com.elva.singleton;
public class Singleton2 {
    private static Singleton2 instance = new Singleton2();
    /**
     * 非懒加载，效率可能会有点问题，当然不关心这个的，可以使用
     * @return
     */
    public static Singleton2 getInstance(){
        return instance;
    }
}

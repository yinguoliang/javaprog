package com.elva.rxjava.hello;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class RxJavaTest2 {
    public static void test1() throws Exception{
        /*
         * Observable是RxJava 1.x就有的类，不包含backpressure处理
         */
        Observable<String> observable=Observable.create(
              new ObservableOnSubscribe<String>(){
                public void subscribe(ObservableEmitter<String> e) throws Exception {
                    e.onNext("Hello, world!");
                    e.onComplete();
                }
              }
           );
        
        Consumer<String> consumer = new Consumer<String>(){
            @Override
            public void accept(String t) throws Exception {
                   System.out.println(">>>>>"+t);
            }};
        
        observable.subscribe(consumer);
    }
    
    public static void main(String args[]) throws Exception {
        test1();
    }
}

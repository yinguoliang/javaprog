package com.elva.rxjava.hello;

import com.elva.util.U;

import io.reactivex.Flowable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

public class RxJavaTest9 {
    public static void main(String args[]) throws Exception{
        Flowable.just("AAA", "BBB", "CCCC")
        .scan(new BiFunction<String,String,String>(){
            public String apply(String t1, String t2) throws Exception {
                U.print(t1+","+t2);
                return t1+"_"+t2;
            }
        })
        .subscribe(new Consumer<String>(){
            public void accept(String t) throws Exception {
                U.print("consume>>"+t);
            }});
        ;
        U.sleep(1000);
    }
}

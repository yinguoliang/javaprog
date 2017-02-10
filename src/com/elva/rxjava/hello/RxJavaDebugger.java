package com.elva.rxjava.hello;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import com.elva.util.U;

public class RxJavaDebugger {
    public static void main(String args[]) throws Exception{
        Flowable.create(new FlowableOnSubscribe<String>(){
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                U.print("generate>>a");
                e.onNext("a");
                U.print("generate>>a");
                e.onNext("b");
                U.print("generate>>a");
                e.onNext("c");
                U.print("generate>>a");
                e.onNext("d");
                U.print(">>>completed");
                e.onComplete();
            }}, BackpressureStrategy.BUFFER)
        .map(new Function<String,String>(){
            public String apply(String t) throws Exception {
                U.print("upper>>"+t);
                return t.toUpperCase();
            }})
        .map(new Function<String,String>(){
            public String apply(String t) throws Exception {
                U.print("multiply>>"+t);
                return t+t;
            }})
        .subscribe(new Consumer<String>(){
            public void accept(String t) throws Exception {
                U.print("consume>>"+t+"\n");
            }})
        ;
    }
}

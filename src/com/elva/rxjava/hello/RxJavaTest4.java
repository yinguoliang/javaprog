package com.elva.rxjava.hello;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.elva.util.CommonUtil;

public class RxJavaTest4 {
    static ExecutorService executor = Executors.newCachedThreadPool();
    public static void main(String args[]) throws Exception{
        Consumer<String> consumer = new Consumer<String>(){
            public void accept(String t) throws Exception {
                System.out.println(CommonUtil.currentThreadName()+":consume>>>>>"+t);
            }
        };
        Flowable.fromCallable(new Callable<String>(){
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println(CommonUtil.currentThreadName()+":emit msg");
                return "MMMMMMMMMMM";
            }})
//        .subscribeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.io())
//        .observeOn(Schedulers.single())
        .observeOn(Schedulers.from(executor))
        .subscribe(consumer);
        ;
        System.out.println(CommonUtil.currentThreadName()+":main");
        Thread.sleep(3000);
    }
}

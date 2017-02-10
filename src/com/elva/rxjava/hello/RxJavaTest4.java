package com.elva.rxjava.hello;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.elva.util.CommonUtil;

public class RxJavaTest4 {
    static ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactory(){
        public Thread newThread(Runnable r) {
            return new Thread("executor_1");
        }});
    static ExecutorService executor2 = Executors.newSingleThreadExecutor(new ThreadFactory(){
        public Thread newThread(Runnable r) {
            return new Thread("executor_2");
        }});
    public static void main(String args[]) throws Exception{
        Consumer<String> consumer = new Consumer<String>(){
            public void accept(String t) throws Exception {
                System.out.println(CommonUtil.currentThreadName()+":consume>>>>>"+t);
            }
        };
        Flowable.fromCallable(new Callable<String>(){
            public String call() throws Exception {
                System.out.println(CommonUtil.currentThreadName()+":emit msg");
                return "MMMMMMMMMMM";
            }})
        .observeOn(Schedulers.newThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(consumer);
        ;
        System.out.println(CommonUtil.currentThreadName()+":main");
    }
}

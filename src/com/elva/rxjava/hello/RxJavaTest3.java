package com.elva.rxjava.hello;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RxJavaTest3 {
    public static void main(String args[]) throws Exception{
        Flowable.just("Hello RxJava")
        .subscribe(new Subscriber<String>(){
            public void onSubscribe(Subscription s) {
                s.request(1);
            }
            public void onNext(String t) {
                System.out.println(">>>>>"+t);
            }
            public void onError(Throwable t) {
                
            }
            public void onComplete() {
                
            }});
        ;
        
        Consumer<String> consumer= new Consumer<String>(){
            public void accept(String t) throws Exception {
                System.out.println(">>>>> consume "+t);
          }};
        Flowable.just("Hello RxJava2 [consumer]").subscribe(consumer);
        Observable.just("Hello RxJava3 from observable").subscribe(consumer);
        Single.just("Hello RxJava2 from single").subscribe(consumer);
        Maybe.just("Hello RxJava2 from Maybe").subscribe(consumer);
    }
}

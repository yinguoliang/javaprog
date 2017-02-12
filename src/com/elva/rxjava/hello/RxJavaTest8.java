package com.elva.rxjava.hello;

import java.util.concurrent.TimeUnit;

import com.elva.util.U;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest8 {
	public static void main(String args[]) throws Exception{
		Flowable.interval(1, TimeUnit.SECONDS)
		.take(20)
		.window(3,TimeUnit.SECONDS)
		.subscribeOn(Schedulers.io())
		.subscribe(new Consumer<Flowable<Long>>(){
			public void accept(Flowable<Long> f) throws Exception {
				U.print("data coming::::");
				f.subscribe(new Consumer<Long>(){
					public void accept(Long t) throws Exception {
						U.print(t);
					}});
			}})
		;
		U.sleep(30000);
	}
}

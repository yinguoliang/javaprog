package com.elva.rxjava.hello;

import org.reactivestreams.Publisher;

import com.elva.util.CommonUtil;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest5 {
	public static void main(String args[]) throws Exception{
		Flowable.range(1, 10)
		.observeOn(Schedulers.computation())
		.map(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				CommonUtil.print(":::multiply "+t);
				return t*t;
			}})
		.blockingSubscribe(new Consumer<Integer>(){
			public void accept(Integer t) throws Exception {
				CommonUtil.sleep(1000);
				CommonUtil.print(":::consume "+t);
			}});
		;
		CommonUtil.print("****************************************");
		Flowable.range(1, 10)
		.flatMap(new Function<Integer,Publisher<Integer>>(){
			public Publisher<Integer> apply(Integer t) throws Exception {
				CommonUtil.print("::::publisher>>"+t);
				return Flowable.just(t)
						.subscribeOn(Schedulers.computation())
						.map(new Function<Integer,Integer>(){
					public Integer apply(Integer t) throws Exception {
						CommonUtil.print("::::apply "+t);
						return t*t;
					}});
			}})
		.blockingSubscribe(new Consumer<Integer>(){
			public void accept(Integer t) throws Exception {
				CommonUtil.sleep(1000);
				CommonUtil.print(":::consume "+t);
			}});
		;
	}
}

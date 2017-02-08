package com.elva.rxjava.hello;

import java.util.Arrays;
import java.util.List;

import org.reactivestreams.Publisher;

import com.elva.util.U;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaTest6 {
	public static void main(String args[]) throws Exception{
		Observable.fromArray(1,2,3,4,5)
		.map(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				U.print("::apply function "+t);
				return t+1;
			}})
		.subscribe()
		;
		U.print("*****************************");
		Observable.fromArray(1,2,3,4,5)
		.flatMap(new Function<Integer,Observable<Integer>>(){
			public Observable<Integer> apply(Integer t) throws Exception {
				return Observable.just(t);
			}})
		.subscribe(new Consumer<Integer>(){
			public void accept(Integer t) throws Exception {
				U.print(":::consume "+t);
			}});
		;
		U.print("******************************");
		Flowable.fromArray(Arrays.asList(1,2,3,4,5),0,Arrays.asList(1,2,3,4,5))
		.observeOn(Schedulers.computation())
		.flatMap(new Function<Object,Publisher<Integer>>(){
			@SuppressWarnings("unchecked")
			public Publisher<Integer> apply(Object t) throws Exception {
				U.print(">>>>apply");
				if(t instanceof Iterable){
					return Flowable.fromIterable((Iterable<Integer>)t);
				}
				return Flowable.just((Integer)t);
			}})
		.blockingSubscribe(new Consumer<Integer>(){
			public void accept(Integer t) throws Exception {
				U.print(":::consume3>>"+t);
			}})
		;
	}
}

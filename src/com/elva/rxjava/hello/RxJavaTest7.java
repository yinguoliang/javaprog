package com.elva.rxjava.hello;

import java.util.List;

import com.elva.util.U;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

public class RxJavaTest7 {
	public static void main(String args[]) throws Exception{
		Observable.range(1, 20)
		.groupBy(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				return t%3;
			}})
		.subscribe(new Consumer<GroupedObservable<Integer,Integer>>(){
			public void accept(GroupedObservable<Integer, Integer> group) throws Exception {
				group.subscribe(new Consumer<Integer>(){
					public void accept(Integer t) throws Exception {
						U.print(group.getKey()+":"+t);
					}})
				;
			}})
		;
		U.print("----------------------------------------");
		Observable.range(1, 20)
		.groupBy(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				return t%3;
			}})
		.buffer(5)
		.subscribe(new Consumer<List<GroupedObservable<Integer,Integer>>>(){
			public void accept(List<GroupedObservable<Integer, Integer>> groups) throws Exception {
				for(GroupedObservable<Integer, Integer> group : groups)
				group.subscribe(new Consumer<Integer>(){
					public void accept(Integer t) throws Exception {
						U.print(group.getKey()+":"+t);
					}})
				;
			}})
		;
	}
}

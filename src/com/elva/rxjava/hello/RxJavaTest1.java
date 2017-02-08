package com.elva.rxjava.hello;

import com.elva.util.CommonUtil;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class RxJavaTest1 {
	public static void main(String args[]) throws Exception{
		CommonUtil.print("*************不调用apply函数*************");
		Flowable.range(1, 10)
		.map(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				CommonUtil.print("::apply "+t);
				return t*t;
			}})
		;
		
		CommonUtil.print("*************调动apply函数******************");
		Flowable.range(1, 10)
		.map(new Function<Integer,Integer>(){
			public Integer apply(Integer t) throws Exception {
				CommonUtil.print("::apply "+t);
				return t*t;
			}})
		.subscribe()
		;
	}
}

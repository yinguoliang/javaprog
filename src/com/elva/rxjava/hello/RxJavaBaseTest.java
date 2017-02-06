package com.elva.rxjava.hello;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RxJavaBaseTest {
	public static void test1() throws Exception{
		Flowable<String> flowable=
		Flowable.create(new FlowableOnSubscribe<String>(){
			public void subscribe(FlowableEmitter<String> e) throws Exception {
				e.onNext("hello rxjava2");
				e.onComplete();
			}}, BackpressureStrategy.BUFFER);
		
		
		Flowable<String> flowable2 = Flowable.just("just method");
		
		Subscriber<String> sub = new Subscriber<String>(){
			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("onSubcribe");
				s.request(10000000);
			}

			@Override
			public void onNext(String t) {
				System.out.println(t);
			}

			@Override
			public void onError(Throwable t) {
				
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}};
			
			
			Consumer<String> consumer = new Consumer<String>(){

				@Override
				public void accept(String t) throws Exception {
					System.out.println("consume...."+t);
					
				}
				
			};
			
			
			Flowable<String> flowable3 = Flowable.just("≤‚ ‘map").map(new Function<String,String>(){
				@Override
				public String apply(String t) throws Exception {
					return t+"---from map";
				}});
			
			flowable.subscribe(sub);
			flowable2.subscribe(sub);
			flowable.subscribe(consumer);
			flowable2.subscribe(consumer);
			flowable3.subscribe(consumer);
	}
	
	final static BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	public static void test2(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=0;i<10;i++){
					try {
						queue.put("MSG-"+i);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				queue.offer("exit");
			}}).start();
		Flowable<String> flowable=
				Flowable.create(new FlowableOnSubscribe<String>(){
					public void subscribe(FlowableEmitter<String> e) throws Exception {
						while(true){
							String msg = queue.take();
							if("exit".equals(msg)){
								break;
							}
							e.onNext(msg);
						}
						e.onComplete();
					}}, BackpressureStrategy.BUFFER);
		
		Consumer<String> consumer = new Consumer<String>(){
			public void accept(String t) throws Exception {
				System.out.println("consume...."+t);
			}
		};
		
		Flowable<String> flowable2 = flowable.map(new Function<String,String>(){
			@Override
			public String apply(String t) throws Exception {
				return t+"::22";
			}});
		
		flowable2.subscribe(consumer);
		flowable.subscribe(consumer);
	}
	public static void main(String args[]) throws Exception{
		test2();
	}
}

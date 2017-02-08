package com.elva.guava.eventbus;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class EventBusTest {
	static class Message{
		public String msg;
		public Message(String msg){
			this.msg = msg;
		}
		public String toString(){
			return msg;
		}
	}
	static class EventListner {
		@Subscribe
		public void listen(Message msg) throws Exception{
			msg.msg=msg.msg+"11111";
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+":hello "+msg);
		}
		/**
		 * AllowConcurrentEvents : ��ע���ʾ�������̰߳�ȫ��
		 * ���û�б�AllowConcurrentEventsע�⣬guava�����ɴ����ʱ�򣬻����synchronized�ؼ���
		 * 
		 * @param msg
		 * @throws Exception
		 */
		@Subscribe
		@AllowConcurrentEvents
		public void welldone(Message msg) throws Exception{
			msg.msg=msg.msg+"2222";
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName()+":well done "+msg);
		}
	}
	public static void test1() throws Exception{
	    //EventBus��ͬ���ģ���post��subcribe������ͬһ���߳���ִ�е�
	    //���Ҫ�첽�Ļ�������ʹ��AsyncEventBus�� ��test2()
		EventBus eventBus = new EventBus("test");
		eventBus.register(new EventListner());
		System.out.println(Thread.currentThread().getName()+": post msg");
		eventBus.post(new Message("HHHH"));
		System.out.println(Thread.currentThread().getName()+": post msg");
		eventBus.post(new Message("IIII"));
		System.out.println(Thread.currentThread().getName()+": post msg");
		eventBus.post(new Message("JJJJ"));
	}
	
	public static void test2() throws Exception{
	    //AsyncEventBus���첽�ģ��̳���EventBus
	    //�޸��˲��ַ�����ʵ��
	    AsyncEventBus eventBus = new AsyncEventBus("test",Executors.newSingleThreadExecutor());
        eventBus.register(new EventListner());
        System.out.println(Thread.currentThread().getName()+": post msg");
        eventBus.post(new Message("HHHH"));
        System.out.println(Thread.currentThread().getName()+": post msg");
        eventBus.post(new Message("IIII"));
        System.out.println(Thread.currentThread().getName()+": post msg");
        eventBus.post(new Message("JJJJ"));
	}
	public static void main(String args[]) throws Exception{
	    test1();
	}
}

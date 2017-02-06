package com.elva.guava.eventbus;

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
		public void listen(Message msg){
			msg.msg=msg.msg+"11111";
			System.out.println("hello "+msg);
		}
		@Subscribe
		public void welldone(Message msg){
			msg.msg=msg.msg+"2222";
			System.out.println("well done "+msg);
		}
	}
	public static void test1() throws Exception{
		EventBus eventBus = new EventBus("test");
		eventBus.register(new EventListner());
		
		eventBus.post(new Message("HHHH"));
	}
	public static void main(String args[]) throws Exception{
		test1();
	}
}

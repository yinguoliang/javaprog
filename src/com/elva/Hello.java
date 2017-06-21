package com.elva;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

import com.elva.util.CommonUtil;
import com.elva.util.U;


public class Hello {
    static volatile Semaphore semaphore = new Semaphore(10);
    static BlockingQueue<Object> queue = new ArrayBlockingQueue<Object>(1000);
    private void start(){
        if(!semaphore.tryAcquire()){
            U.print("no resource");
            return;
        }
        U.print("get resource");
        new Thread(new Runnable(){
            public void run() {
                try{
                    while(true){
                        Object r = queue.poll();
                        if(r==null) break;
                        CommonUtil.sleep(500);
                        System.out.println("consume:::"+r);
                    }
                }finally{
                    semaphore.release();
                }
            }},"Thread-ftp").start();
    }
    public void test(){
        for(int i=0;i<100;i++){
            if(i==50) CommonUtil.sleep(5000);
            queue.add("RRRRR"+i);
            start();
        }
    }
    enum Factory{
        DATE ("date"){
           public void hello(){
               System.out.println("this is date");
           }
        },
        STR ("str"){
            public void hello(){
                System.out.println("this is str");
            }
         }
        ;
        private Factory(String name){
            System.out.println("ENUM CREATED:::::"+name);
        }
        public abstract void hello();
    }
	public static void main(String[] args) throws Exception {
	    
	}
}

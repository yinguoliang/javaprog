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
	public static void main(String[] args) throws Exception {
	    Random r = new Random();
	    System.out.println(r.nextInt(100));
	    r = new Random(1000);
	    System.out.println(r.nextInt(100));
	    r = new Random(1000);
	    System.out.println(r.nextInt(100));
	    r = new Random(1000);
	    System.out.println(r.nextInt(100));
	    r = new Random(1000);
	    System.out.println(r.nextInt(100));
	    System.out.println(r.nextInt(100));
	    System.out.println(r.nextInt(100));
	    r.nextGaussian();
	}
}

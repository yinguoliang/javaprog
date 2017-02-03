//package com.elva.jcsp;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//import org.jcsp.lang.CSProcess;
//import org.jcsp.lang.ChannelInput;
//
//public class ReadEvenIntsProcess implements CSProcess{
//    static AtomicInteger counter = new AtomicInteger();
//    private ChannelInput in;
//    public ReadEvenIntsProcess(ChannelInput in){
//        this.in = in;
//    }
//    @Override
//    public void run() {
//        for(;;){
//            Integer i = (Integer)in.read();
//            System.out.println(counter.incrementAndGet()+":read from channel:"+i);
//        }
//    }
//
//}

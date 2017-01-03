package com.elva.jcsp;

import java.util.concurrent.atomic.AtomicInteger;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.Channel;
import org.jcsp.lang.ChannelInputInt;
import org.jcsp.lang.ChannelOutputInt;
import org.jcsp.lang.One2OneChannelInt;
import org.jcsp.lang.Parallel;
import org.jcsp.plugNplay.ints.Delta2Int;
import org.jcsp.plugNplay.ints.PlusInt;
import org.jcsp.plugNplay.ints.PrefixInt;


public class IntegrateInt implements CSProcess {
    static AtomicInteger counter = new AtomicInteger();
    private final ChannelInputInt in;
    private final ChannelOutputInt out;
    public IntegrateInt(ChannelInputInt in,ChannelOutputInt out){
        this.in = in;
        this.out = out;
    }
    public void run() {
        One2OneChannelInt a = Channel.one2oneInt();
        One2OneChannelInt b = Channel.one2oneInt();
        One2OneChannelInt c = Channel.one2oneInt();
        new Parallel(new CSProcess[]
        {
            new PlusInt(in,c.in(),a.out()),
            new Delta2Int(a.in(),out,b.out()),
            new PrefixInt(0,b.in(),c.out())
        }).run();
    }
    
    public static void test1(){

        final One2OneChannelInt chan = Channel.one2oneInt();
        IntegrateInt p = new IntegrateInt(chan.in(),chan.out());
        new Parallel(new CSProcess[]{
                p,
                new CSProcess(){
                    public void run() {
                        for(int i=0;i<21;i++){
//                            try {
//                                Thread.sleep(1000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                            chan.out().write(i);
                        }
                    }
                },
                new CSProcess(){
                    public void run() {
                        for(;;){
                            System.out.println(counter.incrementAndGet()+":"+chan.in().read());
                        }
                    }
                }
          }).run();
    
    }
    
    public static  void testDeltaInt(){
        final One2OneChannelInt chan = Channel.one2oneInt();
        final One2OneChannelInt out1 = Channel.one2oneInt();
        final One2OneChannelInt out2 = Channel.one2oneInt();
                
        new Parallel(new CSProcess[]{
                new Delta2Int(chan.in(),out1.out(),out2.out()),
                new CSProcess(){
                    public void run() {
                        chan.out().write(1);
                        chan.out().write(2);
                        chan.out().write(3);
                    }
                },
                new CSProcess(){
                    public void run() {
                        for(;;)
                        System.out.println(out1.in().read()+","+out2.in().read());
                    }
                }
        }).run();
    }
    
    public static void testPlusInt(){
        final One2OneChannelInt chan1 = Channel.one2oneInt();
        final One2OneChannelInt chan2 = Channel.one2oneInt();
        final One2OneChannelInt chan3 = Channel.one2oneInt();
        new Parallel(new CSProcess[]{
            new PlusInt(chan1.in(),chan2.in(),chan3.out()),
            new CSProcess(){
                public void run() {
                    for(int i=0;i<3;i++){
                        chan1.out().write(i);
                    }
                }},
            new CSProcess(){
                public void run() {
                    for(;;){
                        chan2.out().write(3);
                    }
                }},
            new CSProcess(){
                public void run(){
                    for(;;){
                        System.out.println(chan3.in().read());
                    }
                }
            }
        }).run();
    }
    
    public static void main(String args[]) throws Exception{
        testPlusInt();
    }
}

package com.elva.jcsp;

import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;

public class SendEvenIntsProcess implements CSProcess {
    private ChannelOutput out;
    
    public SendEvenIntsProcess(ChannelOutput out){
        this.out = out;
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            out.write(new Integer(i));
        }
    }
}

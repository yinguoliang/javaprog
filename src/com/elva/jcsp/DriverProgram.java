//package com.elva.jcsp;
//
//import org.jcsp.lang.Any2AnyChannel;
//import org.jcsp.lang.CSProcess;
//import org.jcsp.lang.Channel;
//import org.jcsp.lang.Parallel;
//
//public class DriverProgram {
//    public static void main(String args[]) throws Exception{
//        final Any2AnyChannel chan = Channel.any2any();
//        new Parallel(
//              new CSProcess[]{
//                      new SendEvenIntsProcess(chan.out()),
//                      new SendEvenIntsProcess(chan.out()),
//                      new SendEvenIntsProcess(chan.out()),
//                      new SendEvenIntsProcess(chan.out()),
//                      new ReadEvenIntsProcess(chan.in()),
//                      new ReadEvenIntsProcess(chan.in())
//              }).run();
//    }
//}

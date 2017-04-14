package com.elva.bytebuffer;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileChannelTest {
    static void p(Object msg){
        System.out.println(msg);
    }
    public static void testWrite() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        p(fc.position());
        p(fc.size());
        p("*********truncate file**********");
        fc.truncate(0);
        p(fc.position());
        p(fc.size());
        p("*********write�ı�position**********");
        fc.write(ByteBuffer.wrap("abcd".getBytes()));
        p(fc.position());
        p(fc.size());
        p("********readҲ�ı�position*************");
        ByteBuffer dst = ByteBuffer.allocate((int)fc.size());
        fc.position(0);//position���õ���ʼλ��
        int len = fc.read(dst);
        p("read len = "+len);
        p(fc.size());
        p(fc.position());//position�ı���
        p(new String(dst.array()));
    }
    public static void main(String args[]) throws Exception{
        testWrite();
    }
}

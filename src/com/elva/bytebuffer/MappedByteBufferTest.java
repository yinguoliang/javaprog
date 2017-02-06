package com.elva.bytebuffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
@SuppressWarnings("resource")
public class MappedByteBufferTest {
    /**
     * 测试MappedByteBuffer写
     * @throws Exception  
     */
    public static void testWrite() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //将文件从0开始，长度10byte的大小映射到内存
        MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, 0  ,  10);
        //然后就可以对内存进行读写了
        byte b = (byte)'A';
        for(int i=0;i<10;i++){
            mappedByteBuffer.put(((byte)(b+i)));
        }
        fc.close();//此时打开文件，看到的内容时ABCDEFGHIJ
    }
    public static void testWrite2() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //将文件从5开始，长度10 byte 映射到内存
        MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, 5  ,  10);
        //然后就可以对内存进行读写了
        byte b = (byte)'A';
        for(int i=0;i<10;i++){
            mappedByteBuffer.put(((byte)(b+i)));
        }
        //此时打开文件，看到的是ABCDEABCDEFGHIJ
        //从第5个位置开始，被覆盖
        fc.close();
    }
    public static void testRead() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //从文件的第二个byte开始，映射5个byte到内存（内存空间仅占5byte)
        MappedByteBuffer mappedByteBuffer=fc.map(MapMode.READ_ONLY, 2, 5);
        //读出5个byte
        for(int i=0;i<5;i++){
            System.out.println((char)mappedByteBuffer.get());
        }
        fc.close();
    }
    public static void main(String args[]) throws Exception{
//        testWrite();
//        testWrite2();
        testRead();
    }
}

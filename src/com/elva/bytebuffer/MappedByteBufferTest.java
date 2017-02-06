package com.elva.bytebuffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
@SuppressWarnings("resource")
public class MappedByteBufferTest {
    /**
     * ����MappedByteBufferд
     * @throws Exception  
     */
    public static void testWrite() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //���ļ���0��ʼ������10byte�Ĵ�Сӳ�䵽�ڴ�
        MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, 0  ,  10);
        //Ȼ��Ϳ��Զ��ڴ���ж�д��
        byte b = (byte)'A';
        for(int i=0;i<10;i++){
            mappedByteBuffer.put(((byte)(b+i)));
        }
        fc.close();//��ʱ���ļ�������������ʱABCDEFGHIJ
    }
    public static void testWrite2() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //���ļ���5��ʼ������10 byte ӳ�䵽�ڴ�
        MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, 5  ,  10);
        //Ȼ��Ϳ��Զ��ڴ���ж�д��
        byte b = (byte)'A';
        for(int i=0;i<10;i++){
            mappedByteBuffer.put(((byte)(b+i)));
        }
        //��ʱ���ļ�����������ABCDEABCDEFGHIJ
        //�ӵ�5��λ�ÿ�ʼ��������
        fc.close();
    }
    public static void testRead() throws Exception{
        FileChannel fc = new RandomAccessFile("d:/hello","rw").getChannel();
        //���ļ��ĵڶ���byte��ʼ��ӳ��5��byte���ڴ棨�ڴ�ռ��ռ5byte)
        MappedByteBuffer mappedByteBuffer=fc.map(MapMode.READ_ONLY, 2, 5);
        //����5��byte
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

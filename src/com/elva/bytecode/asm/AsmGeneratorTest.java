package com.elva.bytecode.asm;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
@SuppressWarnings("rawtypes")
public class AsmGeneratorTest {
	public void generate() throws Exception{
		System.out.println("aaaaaaaaaaa");
		ClassWriter writer = new ClassWriter(0);
		//ͷ��
		writer.visit(
				Opcodes.V1_5, 
				Opcodes.ACC_PUBLIC+Opcodes.ACC_INTERFACE, 
				"com/my/DyGem",
				null,
				"java/lang/Object",
				null);
		//��������
		writer.visitField(
				Opcodes.ACC_PUBLIC,
				"ver", 
				"I",
				null,
				new Integer(100)
				).visitEnd();
		//���巽��
		MethodVisitor mv = writer.visitMethod(
				Opcodes.ACC_PUBLIC,
				"getVer",
				"(Ljava/lang/Object;)I",
				null, 
				null
				);
		mv.visitEnd();
		//
		writer.visitEnd();
		byte[] data = writer.toByteArray();
		File file = new File("f:/temp/DyGen.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
	}
	public byte[] generate2() throws Exception{
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		//ͷ��
		writer.visit(
				Opcodes.V1_5, 
				Opcodes.ACC_PUBLIC, 
				"Test",
				null,
				"java/lang/Object",
				null);
		//�����ʼ�������������У������޷���ʼ����
		//��������ڹ��캯���е���Object.init����
		MethodVisitor init = writer.visitMethod(
				Opcodes.ACC_PUBLIC,
				"<init>",
				"()V", null, null);
		init.visitCode();
		init.visitVarInsn(Opcodes.ALOAD, 0);//0 ��ʾ��ǰ����
		init.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","()V");
		init.visitInsn(Opcodes.RETURN);
		init.visitMaxs(0, 0);
		init.visitEnd();
		//����add����
		MethodVisitor add = writer.visitMethod(
				Opcodes.ACC_PUBLIC,
				"add",
				"(II)I",
				null, 
				null
				);
		add.visitCode();
		add.visitVarInsn(Opcodes.ILOAD, 1);//���iload_1ָ��
		add.visitVarInsn(Opcodes.ILOAD, 2);//���iload_2ָ��
		add.visitInsn(Opcodes.IADD);//���iaddָ��
		add.visitInsn(Opcodes.IRETURN);//���ireturnָ��
		add.visitMaxs(2, 2);//(maxStack,maxLocals)
		add.visitEnd();
		//
		writer.visitEnd();
		byte[] data = writer.toByteArray();
		
		File file = new File("f:/temp/DyGenCls.class");
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
		
		return data;
	}
	public Class genClass(final byte[] bs) throws Exception{
		ClassLoader myloader = new ClassLoader(){
			@Override
			protected Class<?> findClass(String name)throws ClassNotFoundException { 
				return defineClass(name, bs,0,bs.length);
			}
		};
		return myloader.loadClass("Test");
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		AsmGeneratorTest asm = new AsmGeneratorTest();
		Class cls = asm.genClass(asm.generate2());
		Object obj = cls.newInstance();
		Method m = cls.getDeclaredMethod("add", int.class,int.class);
		System.out.println(m.invoke(obj, 1,3));
	}
}

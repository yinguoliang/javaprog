package com.elva.bytecode.asm;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AsmUpdaterTest {
	public void updateClass() throws Exception{
		System.out.println("aaaaaaaaaaa");
		FileInputStream in = new FileInputStream("f:/temp/Hello.class");
		// ClassReader����ͷ��βɨ���ֽ��룬�����ûص���������ɨ�赽���ֽ���
		ClassReader reader = new ClassReader(in);
		// ClassWriterͨ��visitXXX������ͷ��β�����ֽ��룬�����䱣��
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		//ClassAdapter:��ǿ��������ClassVisitor��
		//���ﴫ�����ClassWriter:writer
		ClassAdapter adapter = new ClassAdapter(writer){
			private String owner;
			private boolean isInterface;
			public void visit(int version, int access, String name, String signature,
				String superName, String[] interfaces) {
				owner = "Timed"+name;
				cv.visit(version, access, owner, signature, superName, interfaces);
				isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
			}
			public MethodVisitor visitMethod(int access, String name, String desc,
				String signature, String[] exceptions) {
				MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
				if(!name.equals("<init>") && !isInterface && mv!=null){
					//Ϊ������Ӽ�ʱ����
					mv = new MethodAdapter(mv){
						/**
						 * �ڷ�������ǰ�����
						 *     timer -= System.currentTimeMillis();
						 * ��ָ��
						 */
						public void visitCode() {
							mv.visitCode();
							mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
							mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
							mv.visitInsn(Opcodes.LSUB);
							mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
						}
						/**
						 * ��returnָ��֮ǰ�����
						 *       timer += System.currentTimeMillis();
						 * ��ָ��
						 */
						public void visitInsn(int opcode) {
							if((opcode>=Opcodes.IRETURN && opcode<=Opcodes.RETURN) || opcode==Opcodes.ATHROW){
								mv.visitFieldInsn(Opcodes.GETSTATIC, owner, "timer", "J");
								mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J");
								mv.visitInsn(Opcodes.LADD);
								mv.visitFieldInsn(Opcodes.PUTSTATIC, owner, "timer", "J");
							}
							 mv.visitInsn(opcode);
						}
						public void visitMaxs(int maxStack, int maxLocal) {
							mv.visitMaxs(maxStack+4, maxLocal);
						}
					};
				}
				return mv;
			}
			public void visitEnd() {
				if(!isInterface){
					FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC+Opcodes.ACC_STATIC, "timer", "J", null, null);
					if(fv!=null){
						 fv.visitEnd();
					}
				}
				cv.visitEnd();
			}
		};
		reader.accept(adapter, ClassReader.SKIP_DEBUG);
		
		byte[] bytes = writer.toByteArray();
		FileOutputStream fos = new FileOutputStream("f:/temp/AddTimeTest.class");
		fos.write(bytes);
		fos.close();
		
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		new AsmUpdaterTest().updateClass();
	}

}

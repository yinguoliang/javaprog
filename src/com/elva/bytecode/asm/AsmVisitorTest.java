package com.elva.bytecode.asm;

import java.io.FileInputStream;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class AsmVisitorTest {
	class MyVisitor implements ClassVisitor{

		public void visit(int arg0, int arg1, String arg2, String arg3,
				String arg4, String[] arg5) {
			System.out.println("aaaaaaaaaaa");
			System.out.println("******* visit class: "+arg2+" **********");
		}

		public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
			System.out.println("******* visit visitAnnotation: "+arg0+" **********");
			return null;
		}

		public void visitAttribute(Attribute arg0) {
			System.out.println("******* visit Attribute: "+arg0+" **********");
		}

		public void visitEnd() {
			System.out.println("******* visit visitEnd **********");
		}

		public FieldVisitor visitField(int arg0, String arg1, String arg2,
				String arg3, Object arg4) {
			System.out.println("******* visit visitField: "+arg1+" **********");
			return null;
		}

		public void visitInnerClass(String arg0, String arg1, String arg2,
				int arg3) {
			System.out.println("******* visit visitInnerClass: "+arg0+" **********");
		}

		public MethodVisitor visitMethod(int arg0, String arg1, String arg2,
				String arg3, String[] arg4) {
			System.out.println("******* visit visitMethod: "+arg1+" **********");
			return null;
		}

		public void visitOuterClass(String arg0, String arg1, String arg2) {
			
		}

		public void visitSource(String arg0, String arg1) {
			System.out.println("******* visit visitSource: "+arg0+" **********");
		}
		
	}
	public void read() throws Exception{
		FileInputStream in = new FileInputStream("f:/temp/Test.class");
		ClassReader reader = new ClassReader(in);
		reader.accept(new MyVisitor(), ClassReader.SKIP_DEBUG);
	}
	public static void main(String args[]) throws Exception{
		new AsmVisitorTest().read();
	}
}

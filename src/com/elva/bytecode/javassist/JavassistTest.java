package com.elva.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import com.elva.Hello;

public class JavassistTest {
	public static void main(String[] args) throws Exception{
		//http://www.iteye.com/topic/53740
		////����ȡ���ֽ����࣬�����ڵ�ǰ��classpath�У�ʹ��ȫ�� 
		CtClass ctClass = ClassPool.getDefault().get("com.elva.Hello"); 
		//��Ҫ�޸ĵķ���
		//�����ȡ�����޲η����������Ҫ��ȡ�вη����������ں������CtClass�������
		CtMethod helloMethod = ctClass.getDeclaredMethod("sayHello");
		//�޸�ԭ�еķ�������
		helloMethod.setName("sayHelloOld");
		//�����µķ���������ԭ���ķ���
		CtMethod newMethod = CtNewMethod.copy(helloMethod, "sayHello", ctClass, null);
		
		//�·����ķ�����
		StringBuffer body = new StringBuffer();
		body.append(
				 "{   System.out.print(\"**************new method ***********\\n\");  " 
				+"    long start = System.currentTimeMillis();" 
				+"    sayHelloOld();       "  //����ԭ�д��룬������method();($$)��ʾ���еĲ���  
				+"    long tookTimes = System.currentTimeMillis()-start;    "
				+"    System.out.println(\"Call to method sayHelloNew took \" + tookTimes + \" ms.\");"
				+"}"
				);  
		
		newMethod.setBody(body.toString());
		//�ڷ�����ǰ��ֱ�Ӳ���Դ��
		newMethod.insertAfter("System.out.println(\"---after--\");");
		newMethod.insertBefore("System.out.println(\"---before----\");");
		ctClass.addMethod(newMethod);
		
		Hello hello=(Hello)ctClass.toClass().newInstance();
		hello.sayHello();
	}

}

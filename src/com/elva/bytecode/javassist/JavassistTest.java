package com.elva.bytecode.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

import com.elva.Hello;

public class JavassistTest {
	public static void main(String[] args) throws Exception{
		//http://www.iteye.com/topic/53740
		////用于取得字节码类，必须在当前的classpath中，使用全称 
		CtClass ctClass = ClassPool.getDefault().get("com.elva.Hello"); 
		//需要修改的方法
		//这里获取的是无参方法，如果需要获取有参方法，可以在后面添加CtClass数组参数
		CtMethod helloMethod = ctClass.getDeclaredMethod("sayHello");
		//修改原有的方法名称
		helloMethod.setName("sayHelloOld");
		//创建新的方法，复制原来的方法
		CtMethod newMethod = CtNewMethod.copy(helloMethod, "sayHello", ctClass, null);
		
		//新方法的方法体
		StringBuffer body = new StringBuffer();
		body.append(
				 "{   System.out.print(\"**************new method ***********\\n\");  " 
				+"    long start = System.currentTimeMillis();" 
				+"    sayHelloOld();       "  //调用原有代码，类似于method();($$)表示所有的参数  
				+"    long tookTimes = System.currentTimeMillis()-start;    "
				+"    System.out.println(\"Call to method sayHelloNew took \" + tookTimes + \" ms.\");"
				+"}"
				);  
		
		newMethod.setBody(body.toString());
		//在方法的前后直接插入源码
		newMethod.insertAfter("System.out.println(\"---after--\");");
		newMethod.insertBefore("System.out.println(\"---before----\");");
		ctClass.addMethod(newMethod);
		
		Hello hello=(Hello)ctClass.toClass().newInstance();
		hello.sayHello();
	}

}

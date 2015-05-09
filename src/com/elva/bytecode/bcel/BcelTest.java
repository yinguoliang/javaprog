package com.elva.bytecode.bcel;

import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.bcel.Constants;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.LineNumberTable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.InstructionFactory;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.MethodGen;
import org.apache.bcel.generic.ObjectType;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.Type;

public class BcelTest {
	private static void modifyWrapper(ClassGen cgen,Method method){
        InstructionFactory ifact = new InstructionFactory(cgen);
        //java类中的常量池
        ConstantPoolGen pgen = cgen.getConstantPool();
        
        String cname = cgen.getClassName();
        
        MethodGen wrapgen = new MethodGen(method,cname,pgen);
        
        //除掉这个原来的方法
        cgen.removeMethod(method);
        
        System.out.println("打印出这个方法看看---------------------------------start");
        System.out.println(wrapgen.toString());
        System.out.println("打印出这个方法看看---------------------------------end");
        
        
        InstructionList ilist = wrapgen.getInstructionList();
        System.out.println("看看这个方法的InstructionList-----------------------------start");
        System.out.println(ilist.toString());
        System.out.println("看看这个方法的InstructionList-----------------------------start");
        
        System.out.println("看看这个方法InstructionList的各个InstructionHandle的信息--------------------start");
        Iterator handleIt = ilist.iterator();
        
        while(handleIt.hasNext()){
            InstructionHandle iHandle = (InstructionHandle)handleIt.next();
            System.out.println(iHandle.getAttributes());
            System.out.println(iHandle.toString());
        }
        
        System.out.println("看看这个方法InstructionList的各个InstructionHandle的信息--------------------end");
        
        System.out.println("先看看此方法的LocalVariableTable的信息--------------------------------------start");
        LocalVariableTable lvt = wrapgen.getLocalVariableTable(pgen);
        System.out.println(lvt.toString());
        System.out.println("先看看此方法的LocalVariableTable的信息--------------------------------------end");
        
        LineNumberTable lnt = wrapgen.getLineNumberTable(pgen);
        System.out.println("LineNumberTable--------------------------------------start");
        System.out.println(lnt.toString());
        System.out.println("LineNumberTable--------------------------------------end");
        
        
        Type returnType = wrapgen.getReturnType();
        
        Type[] types = wrapgen.getArgumentTypes();
        int slot = wrapgen.isStatic()?0:1;//非静态方法slot 0处应该存储的是this
        //// 这种方式与Java如何处理方法调用有关。对于非静态的方法，每次调用的第一个（隐藏的）参数是目标对象的this引用（就是位置0储存的内容）。
        for(int i = 0;i<types.length;i++){
            slot += types[i].getSize();//long,double的size为2
        }
        
        /*
         * 判断原来的方法用到了哪些局部变量,获得局部变量中已经用到的最大索引
         * 将新产生的局部变量加到最大索引后,本来准备自己写的
         * BCEL中已经这样的方法了
         */
        
        LocalVariableGen lvg = wrapgen.addLocalVariable("starttime", Type.LONG, null, null);
        
        
        //先插入一条打印语句不使用局部变量
        InstructionList printlnList = ifact.createPrintln("start test start test start test start test");
        
        InvokeInstruction invokestatic = ifact.createInvoke("java.lang.System", 
                "currentTimeMillis", Type.LONG, Type.NO_ARGS, Constants.INVOKESTATIC);
        
        
        InstructionHandle firstHandle = printlnList.append(invokestatic);
        //这个会不会将局部变量为2的地方的局部变量给覆盖,然后此局部变量也没有定义名字??slot这个index怎么来确定
        //slot现在应该为2,之前代码块放的是result信息是aload,
        //写到新产生的局部变量的索引处
        printlnList.append(InstructionFactory.createStore(Type.LONG, lvg.getIndex()));
        
        //加入到之前的inlist中
        ilist.insert(printlnList);
        
        //
        
        //在语句最后在打印结束语句,不能简单插入在最后面,加入方法有返回值,则要插入在return 之前
        //在return的时候，还要先将return的引用加入到操作数栈,怎么获取result在局部变量中index
        InstructionHandle insertposition =null;
        if(returnType.getType()!=Type.VOID.getType()){
            insertposition = ilist.getEnd().getPrev();
            
        }else{
            //返回值为Type.void的话,由于没有返回值，因此只需要在return指令前加就够了,往前取一个即可
            insertposition = ilist.getEnd();
        }
        InstructionList tempList = new InstructionList();
        tempList.append(ifact.createFieldAccess("java.lang.System"
                , "out", new ObjectType("java.io.PrintStream"), 
                Constants.GETSTATIC));
        
        tempList.append(InstructionFactory.DUP);
        tempList.append(InstructionFactory.DUP);
        
        String text = "Call to method "+wrapgen.getName()+" took ";
        tempList.append(new PUSH(pgen,text));
        tempList.append(ifact.createInvoke("java.io.PrintStream", 
                "print", Type.VOID, new Type[]{Type.STRING}, 
                Constants.INVOKEVIRTUAL));
        tempList.append(ifact.createInvoke("java.lang.System", 
                "currentTimeMillis", Type.LONG, Type.NO_ARGS, 
                Constants.INVOKESTATIC));
        //获取之前的的starttime局部变量
        tempList.append(InstructionFactory.createLoad(Type.LONG, lvg.getIndex()));
        tempList.append(InstructionConstants.LSUB);
        tempList.append(ifact.createInvoke("java.io.PrintStream", 
                "print", Type.VOID, new Type[]{Type.LONG}, 
                Constants.INVOKEVIRTUAL));
        tempList.append(new PUSH(pgen," ms."));
        tempList.append(ifact.createInvoke("java.io.PrintStream", 
                "println", Type.VOID, new Type[]{Type.STRING}, 
                Constants.INVOKEVIRTUAL));
        
        //在return之前的最后一句是打印end end end.信息
        tempList.append(ifact.createPrintln("end end end end end end end end end end"));
        
        ilist.insert(insertposition,tempList);
        
//        LocalVariableGen lvgen  = new LocalVariableGen(slot,"start",Type.LONG,null,null);
        
        //finalize the construted method
        wrapgen.stripAttributes(false);
        wrapgen.setMaxStack();
        wrapgen.setMaxLocals();

        cgen.addMethod(wrapgen.getMethod());
        
        System.out.println();
        System.out.println();
        System.out.println(wrapgen.getInstructionList());
        ilist.dispose();
        
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		JavaClass jclas = new ClassParser("f:/temp/Hello.class").parse();
		ClassGen cgen = new ClassGen(jclas);
		Method[] methods = jclas.getMethods();
		modifyWrapper(cgen,methods[1]);
		FileOutputStream fos = new FileOutputStream("f:/temp/bcel.class");
		cgen.getJavaClass().dump(fos);
		fos.close();
		
	}

}

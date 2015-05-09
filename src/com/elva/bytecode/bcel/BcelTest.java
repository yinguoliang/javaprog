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
        //java���еĳ�����
        ConstantPoolGen pgen = cgen.getConstantPool();
        
        String cname = cgen.getClassName();
        
        MethodGen wrapgen = new MethodGen(method,cname,pgen);
        
        //�������ԭ���ķ���
        cgen.removeMethod(method);
        
        System.out.println("��ӡ�������������---------------------------------start");
        System.out.println(wrapgen.toString());
        System.out.println("��ӡ�������������---------------------------------end");
        
        
        InstructionList ilist = wrapgen.getInstructionList();
        System.out.println("�������������InstructionList-----------------------------start");
        System.out.println(ilist.toString());
        System.out.println("�������������InstructionList-----------------------------start");
        
        System.out.println("�����������InstructionList�ĸ���InstructionHandle����Ϣ--------------------start");
        Iterator handleIt = ilist.iterator();
        
        while(handleIt.hasNext()){
            InstructionHandle iHandle = (InstructionHandle)handleIt.next();
            System.out.println(iHandle.getAttributes());
            System.out.println(iHandle.toString());
        }
        
        System.out.println("�����������InstructionList�ĸ���InstructionHandle����Ϣ--------------------end");
        
        System.out.println("�ȿ����˷�����LocalVariableTable����Ϣ--------------------------------------start");
        LocalVariableTable lvt = wrapgen.getLocalVariableTable(pgen);
        System.out.println(lvt.toString());
        System.out.println("�ȿ����˷�����LocalVariableTable����Ϣ--------------------------------------end");
        
        LineNumberTable lnt = wrapgen.getLineNumberTable(pgen);
        System.out.println("LineNumberTable--------------------------------------start");
        System.out.println(lnt.toString());
        System.out.println("LineNumberTable--------------------------------------end");
        
        
        Type returnType = wrapgen.getReturnType();
        
        Type[] types = wrapgen.getArgumentTypes();
        int slot = wrapgen.isStatic()?0:1;//�Ǿ�̬����slot 0��Ӧ�ô洢����this
        //// ���ַ�ʽ��Java��δ����������йء����ڷǾ�̬�ķ�����ÿ�ε��õĵ�һ�������صģ�������Ŀ������this���ã�����λ��0��������ݣ���
        for(int i = 0;i<types.length;i++){
            slot += types[i].getSize();//long,double��sizeΪ2
        }
        
        /*
         * �ж�ԭ���ķ����õ�����Щ�ֲ�����,��þֲ��������Ѿ��õ����������
         * ���²����ľֲ������ӵ����������,����׼���Լ�д��
         * BCEL���Ѿ������ķ�����
         */
        
        LocalVariableGen lvg = wrapgen.addLocalVariable("starttime", Type.LONG, null, null);
        
        
        //�Ȳ���һ����ӡ��䲻ʹ�þֲ�����
        InstructionList printlnList = ifact.createPrintln("start test start test start test start test");
        
        InvokeInstruction invokestatic = ifact.createInvoke("java.lang.System", 
                "currentTimeMillis", Type.LONG, Type.NO_ARGS, Constants.INVOKESTATIC);
        
        
        InstructionHandle firstHandle = printlnList.append(invokestatic);
        //����᲻�Ὣ�ֲ�����Ϊ2�ĵط��ľֲ�����������,Ȼ��˾ֲ�����Ҳû�ж�������??slot���index��ô��ȷ��
        //slot����Ӧ��Ϊ2,֮ǰ�����ŵ���result��Ϣ��aload,
        //д���²����ľֲ�������������
        printlnList.append(InstructionFactory.createStore(Type.LONG, lvg.getIndex()));
        
        //���뵽֮ǰ��inlist��
        ilist.insert(printlnList);
        
        //
        
        //���������ڴ�ӡ�������,���ܼ򵥲����������,���뷽���з���ֵ,��Ҫ������return ֮ǰ
        //��return��ʱ�򣬻�Ҫ�Ƚ�return�����ü��뵽������ջ,��ô��ȡresult�ھֲ�������index
        InstructionHandle insertposition =null;
        if(returnType.getType()!=Type.VOID.getType()){
            insertposition = ilist.getEnd().getPrev();
            
        }else{
            //����ֵΪType.void�Ļ�,����û�з���ֵ�����ֻ��Ҫ��returnָ��ǰ�Ӿ͹���,��ǰȡһ������
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
        //��ȡ֮ǰ�ĵ�starttime�ֲ�����
        tempList.append(InstructionFactory.createLoad(Type.LONG, lvg.getIndex()));
        tempList.append(InstructionConstants.LSUB);
        tempList.append(ifact.createInvoke("java.io.PrintStream", 
                "print", Type.VOID, new Type[]{Type.LONG}, 
                Constants.INVOKEVIRTUAL));
        tempList.append(new PUSH(pgen," ms."));
        tempList.append(ifact.createInvoke("java.io.PrintStream", 
                "println", Type.VOID, new Type[]{Type.STRING}, 
                Constants.INVOKEVIRTUAL));
        
        //��return֮ǰ�����һ���Ǵ�ӡend end end.��Ϣ
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

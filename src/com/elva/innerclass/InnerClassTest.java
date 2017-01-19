package com.elva.innerclass;
public class InnerClassTest {
    private int counter=0;
    public void incr(){
        counter++;
    }
    public int getCounter(){
        return counter;
    }
    class Inner{
        public void doIncr(){
            incr();
        }
    }
    
    public static void main(){
        InnerClassTest t = new InnerClassTest();
        /**
         * **********高能预警***************
         * 这里可以看出，内部类实例是依附于外部类实例的
         * 我们不能直接new Outer.Inner()
         * 而只能使用外部类的实例outer.new Inner()
         * 这样，内部类的实例可以直接访问外部类的实例变量
         * 很多框架类都使用了内部类这种特性，完成一些功能。
         * 比如concurrent包里面的shecudeAtFixedRate()就定义了一个内部Task类来封装外部传入的Runnable
         * Task类内部可以直接调用外部的方法，在执行结束后重新加一个任务进去
         */
        InnerClassTest.Inner i = t.new Inner();
        i.doIncr();
    }
}

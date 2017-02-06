package com.elva.guava.reflect;

import java.util.Set;

import com.google.common.reflect.TypeToken;

public class TypeTokenTest {
	static interface I {
		
	}
	static class A implements I{
		
	}
	static class B extends A{
		
	}
	static class C extends B{
		
	}
	
	public static void main(String args[]) throws Exception{
		Set<Class<? super TypeTokenTest.C>> s = TypeToken.of(C.class).getTypes().rawTypes();
		for(Class c : s){
			System.out.println(c.getName());
		}
	}
}

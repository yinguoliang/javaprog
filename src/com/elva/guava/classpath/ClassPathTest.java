package com.elva.guava.classpath;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

public class ClassPathTest {
	public static void main(String args[]) throws Exception{
		ClassPath cp = ClassPath.from(ClassPathTest.class.getClassLoader());
		ImmutableSet<ClassInfo> set = cp.getTopLevelClasses("com.elva");
		for(ClassInfo info : set){
			System.out.println(info.getName());
		}
		
		System.out.println("------------------");
		set = cp.getTopLevelClassesRecursive("com.elva");
		for(ClassInfo info : set){
			System.out.println(info.getName());
		}
//		System.out.println("------------------");
//		set = cp.getTopLevelClasses();
//		for(ClassInfo info : set){
//			System.out.println(info.getName());
//		}
	}
}

package com.elva.guava;

import com.google.common.base.Joiner;

public class TestMain {
	public static void main(String args[]) throws Exception{
		Joiner join = Joiner.on(",").skipNulls();
		System.out.println(join.join("a","b","c"));
	}
}

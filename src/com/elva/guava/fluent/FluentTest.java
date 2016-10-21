package com.elva.guava.fluent;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;

public class FluentTest {
	public static void main(String args[]) throws Exception{
		Function<String,Integer> fun = new Function<String,Integer>(){
			@Override
			public Integer apply(String input) {
				return input.length();
			}
		};
		Predicate<String> allCaps = new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				return CharMatcher.JAVA_UPPER_CASE.matchesAllOf(input);
			}
		};
		List<String> strings = Arrays.asList("DFADFDXX","DDDDDD","DDEEEES");
		Multiset<Integer> sets = HashMultiset.create(
				Iterables.transform(Iterables.filter(strings, allCaps), fun)
			);
		System.out.println(sets);
		
		
		Collection<String> strs = 
				Collections2.filter(Arrays.asList("12","333","332323"), new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				return input.length()>=3;
			}});
		System.out.println(strs);
		
		System.out.println(Iterables.all(strs, new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				return input.length()>3;
			}}));
		System.out.println(Iterables.any(strs, new Predicate<String>(){
			@Override
			public boolean apply(String input) {
				return input.length()>3;
			}}));
	}
}

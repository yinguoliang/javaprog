package com.elva.guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class BaseCacheTest {
    public static void test1() throws ExecutionException{
        CacheLoader<String, String> loader = new CacheLoader<String, String>(){
            public String load(String key) throws Exception {
                System.out.println("loading key: "+key);
                return "L_"+key;
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .maximumSize(3)
                .build(loader);
        System.out.println(cache.get("aaaa"));
        System.out.println(cache.get("aaaa"));
        System.out.println(cache.get("aaaa"));
        System.out.println(cache.get("bbbbb"));
        System.out.println(cache.get("bbbbb"));
        System.out.println(cache.get("cccc"));
        System.out.println(cache.get("cccc"));
        System.out.println(cache.get("ddddd"));
        System.out.println(cache.get("ddddd"));
        System.out.println(cache.get("eeeeee"));
        System.out.println(cache.get("aaaa"));
        System.out.println(cache.get("bbbbb"));
    }
    
    public static void test2() throws ExecutionException{
        Callable<String> call = new Callable<String>(){
            public String call() throws Exception {
                System.out.println("loading key HHH");
                return "LL_HHH";
            }
        };
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .build();
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
        System.out.println(cache.get("bbbbb",call));
    }
    
    public static void main(String args[]) throws Exception{
        test1();
    }
}

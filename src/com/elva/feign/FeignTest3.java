package com.elva.feign;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.Target;
import feign.Target.HardCodedTarget;
import feign.gson.GsonDecoder;

public class FeignTest3 {
    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<JSONObject> contributors2(@Param("owner") String owner, @Param("repo") String repo);
    }
    interface GitHub2 {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<JSONObject> contributors2(@Param("owner") String owner, @Param("repo") String repo);
    }
    interface GitHub3 {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<JSONObject> contributors2(@Param("owner") String owner, @Param("repo") String repo);
    }
    static class Contributor {
        String login;
        int    contributions;
    }
    public void test(){
        /*
         * support multiple interfaces
         */
        Feign.Builder builder = Feign.builder().decoder(new GsonDecoder());
        GitHub github = builder.target(GitHub.class, "https://api.github.com");
        GitHub2 github2 = builder.target(GitHub2.class, "https://api.github.com");
        
        Target<GitHub3> target = new HardCodedTarget<GitHub3>(GitHub3.class, "https://api.github.com");
        GitHub3 github3 = builder.target(target);
        U.print("----------------------------------------------");
        U.print(github.contributors2("netflix", "feign"));
        U.print("----------------------------------------------");
        U.print(github2.contributors2("netflix", "feign"));
        U.print("----------------------------------------------");
        U.print(github3.contributors2("netflix", "feign"));
    }
    public static void main(String args[]) throws Exception{
        new FeignTest3().test();
    }
}

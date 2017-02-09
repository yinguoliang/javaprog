package com.elva.feign;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;

public class FeignTest1 {
    interface GitHub {
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
        GitHub github = Feign.builder()
                             .decoder(new GsonDecoder())
                             .target(GitHub.class, "https://api.github.com");
        List<Contributor> contributors = github.contributors("netflix", "feign");
        for (Contributor contributor : contributors) {
          System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
        
        U.print("----------------------------------------------");
        U.print(github.contributors2("netflix", "feign"));
    }
    public static void main(String args[]) throws Exception{
        new FeignTest1().test();
    }
}

package com.elva.feign;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.gson.GsonDecoder;
import feign.ribbon.LoadBalancingTarget;
import feign.ribbon.RibbonClient;

public class FeignTest4 {
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
        /*
         * Ö¸¶¨client
         */
//        Client client = new OkHttpClient();
        GitHub github = Feign.builder()
                             .decoder(new GsonDecoder())
//                             .client(client)
                             .client(RibbonClient.create())
                             .target(LoadBalancingTarget.create(GitHub.class, "https://api.github.com"));
        U.print("----------------------------------------------");
        U.print(github.contributors2("netflix", "feign"));
    }
    public static void main(String args[]) throws Exception{
        new FeignTest4().test();
    }
}

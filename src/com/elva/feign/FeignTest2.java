package com.elva.feign;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;
import com.google.common.io.CharStreams;

import feign.Feign;
import feign.FeignException;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;

public class FeignTest2 {
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
         * ×Ô¶¨Òådecoder
         */
        GitHub github = Feign.builder()
                             .decoder(new Decoder(){
                                @Override
                                public Object decode(Response response, Type type) throws IOException, DecodeException,
                                        FeignException {
                                    Reader reader = response.body().asReader();
                                    String str = CharStreams.toString(reader);
                                    U.print("response>>>>>"+str);
                                    return null;
                                }})
                             .target(GitHub.class, "https://api.github.com");
        U.print("----------------------------------------------");
        U.print(github.contributors2("netflix", "feign"));
    }
    public static void main(String args[]) throws Exception{
        new FeignTest2().test();
    }
}

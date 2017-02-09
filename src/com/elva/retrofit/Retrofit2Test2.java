package com.elva.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

public class Retrofit2Test2 {
    Converter.Factory convertFactory = new Converter.Factory(){
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                Retrofit retrofit) {
              return new Converter<ResponseBody,Object>(){
                public Object convert(ResponseBody value) throws IOException {
                    String res = value.string();
                    U.print("响应结果转换:"+res);
                    return res;
                }};
        }
    };
    static String baseUrl = "https://api.github.com";
    interface GitHubService{
        @GET("/users/{user}")
        Call<JSONObject> userInfo(@Path("user") String user);
    }
    /**
     * 自定义转换器
     * @throws Exception
     */
    public void test1() throws Exception{
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(convertFactory)
        .build();
        GitHubService service = retrofit.create(GitHubService.class);
        U.print("---------------------------------------------");
        U.print("打印结果:"+service.userInfo("yinguoliang").execute().body());
    }
    /**
     * 自定义转换器
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception{
        new Retrofit2Test2().test1();
    }
}

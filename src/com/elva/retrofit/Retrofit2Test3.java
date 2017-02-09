package com.elva.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

public class Retrofit2Test3 {
    static OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
    static String baseUrl = "https://api.github.com";
    interface GitHubService{
        @GET("/users/{user}")
        Call<JSONObject> userInfo(@Path("user") String user);
    }
    /**
     * 自定义Interceptor
     * @throws Exception
     */
    public void test1() throws Exception{
        Interceptor interceptor = new Interceptor(){
            public Response intercept(Chain chain) throws IOException {
                U.print(">>>>>>>>>>>> intercept ............");
                Request request = chain.request();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=60")
                        .removeHeader("Pragma")
                        .build();
            }};
        okClientBuilder.connectTimeout(3, TimeUnit.MINUTES)
        .addInterceptor(interceptor)
//        .cache(cache)
        ;
        
        
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okClientBuilder.build())
        .build();
        GitHubService service = retrofit.create(GitHubService.class);
        U.print("---------------------------------------------");
        U.print("打印结果:"+service.userInfo("yinguoliang").execute().body());
    }
    public static void main(String args[]) throws Exception{
        new Retrofit2Test3().test1();
    }
}

package com.elva.retrofit;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.elva.util.U;

public class Retrofit2Test1 {
    interface GitHubService{
        @GET("/users/basil2style")
        Call<JSONObject> queryRepo();
        
        @GET("/users/{user}/repos")
        Call<List<JSONObject>> listRepo(@Path("user") String user);
        
        @HTTP(method="GET",path="/users/{user}/repos",hasBody = false)
        Call<List<JSONObject>> listRepo2(@Path("user") String user);
        
        @GET
        Call<ResponseBody> vvv(@Url String url);
        
        ////////其他示例////////
        /*
         * URL:/group/users?id=groupId
         */
        @GET("/group/users")
        Call<Object> testQuery(@Query("id") String gouprId);
        /*
         * URL:/group/users?k1=v1&k2=v2...
         */
        @GET("/group/users")
        Call<Object> testQuery2(@QueryMap Map<String,String> params);//
        /*
         * post方式提交表单
         */
        @FormUrlEncoded
        @POST("/add")
        Call<Object> update(@Field("userName") String p1,@Field("password") String password);//
        /*
         * 下载文件,这里可动态传入URL进行下载
         */
        @Streaming
        @GET
        Call<ResponseBody> download(@Url String fileUrl);
        /*
         * 上传文件
         */
        @Multipart
        @POST("/upload")
        Call<ResponseBody> upload(@Part("description") RequestBody desc,@Part MultipartBody.Part file);
    }
    
    
    /**
     * test1
     * @throws IOException
     * @throws Exception 
     */
    public void test1() throws IOException, Exception{
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
        
        GitHubService service = retrofit.create(GitHubService.class);
        U.print("******************CALL********************");
        Call<JSONObject> call = service.queryRepo();
        U.print(JSON.toJSONString(call.clone().execute().body(),false));
        //**********list repo***********
        U.print("******************LIST REPO********************");
        Call<List<JSONObject>> listRepo = service.listRepo("yinguoliang");
        U.print("repo size:::::"+listRepo.execute().body().size());
      //**********list repo asynchronous***********
        U.print("*****************LIST REPO ASYNCHRONOUS**********");
        final CountDownLatch latch = new CountDownLatch(1);
        Call<List<JSONObject>> asyncListRepo = service.listRepo("yinguoliang");
        asyncListRepo.enqueue(new Callback<List<JSONObject>>(){
            public void onResponse(Call<List<JSONObject>> call, Response<List<JSONObject>> response) {
                U.print("异步执行成功，repo size="+response.body().size());
                U.print(response.headers().get("Content-Type"));
                latch.countDown();
            }
            public void onFailure(Call<List<JSONObject>> call, Throwable t) {
                U.print("异步执行失败:"+t.getMessage());
                latch.countDown();
            }});
        latch.await();
        U.print("******************HTTP ANNO*******************");
        Call<List<JSONObject>> http = service.listRepo2("yinguoliang");
        U.print("http anno test, result="+http.execute().body());
        U.print("******************URL ANNO*********************");
        Call<ResponseBody> vvvRes = service.vvv("https://api.github.com/users/basil2style");
        U.print("vvv res="+JSON.toJSONString(vvvRes.execute().body().contentLength()));
        U.print("******************DOWNLOAD FILE***************");
        String fileUrl = "http://static.zhongan.com/upload/online/bxtk/1449133067865_%E6%80%A5%E6%80%A7%E8%82%A0%E8%83%83%E7%82%8E%E5%81%A5%E5%BA%B7%E4%BF%9D%E9%99%A9%E6%9D%A1%E6%AC%BE.pdf";
        Call<ResponseBody> stream = service.download(fileUrl);
        ResponseBody responseBody = stream.execute().body();
        U.print("download file:::"+responseBody.contentLength());
//        InputStream fis = responseBody.byteStream();
        U.print("******************over************************");
    }
    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) throws Exception{
         new Retrofit2Test1().test1();
    }
}

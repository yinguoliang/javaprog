package com.elva.retrofit;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OKHttpBaseTest {
	/*
	 * ==================test1=========================
	 */
	public static void test1() throws Exception{
		String url = "http://www.dy2018.com/";
		OkHttpClient okClient = new OkHttpClient();
		Request.Builder requestBuilder = new Request.Builder().url(url);
		requestBuilder.method("GET", null);
		okClient
		.newCall(requestBuilder.build())
		.enqueue(new Callback(){
			public void onFailure(Call call, IOException e) {
				System.out.println("fali::"+e.getMessage());
			}
			public void onResponse(Call call, Response response) throws IOException {
				if(response.cacheResponse()!=null){
					System.out.println("CACHE::"+response.cacheResponse().toString());
				}else{
					System.out.println("NNNNN::"+response.body().string());
				}
			}});
		System.out.println("========================================");
		okClient
		.newCall(requestBuilder.build())
		.enqueue(new Callback(){
			public void onFailure(Call call, IOException e) {
				System.out.println("fali::"+e.getMessage());
			}
			public void onResponse(Call call, Response response) throws IOException {
				if(response.cacheResponse()!=null){
					System.out.println("CACHE::"+response.cacheResponse().toString());
				}else{
					System.out.println("NNNNN::"+response.body().string());
				}
			}});
	}
	/**
	 * ====================main=====================
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception{
		test1();
	}
}

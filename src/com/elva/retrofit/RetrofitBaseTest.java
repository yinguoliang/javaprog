package com.elva.retrofit;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import com.alibaba.fastjson.JSON;

class User implements Serializable{
	private static final long serialVersionUID = 1L;
	public String id;
	public String login;
	public String avatar_url;
	public String url;
	public Date created_at;
}
interface QueryService{
	@GET("/users/basil2style")
	Call<User> queryRepo();
	
	@GET("/users/basil2style")
	Response<User> queryUser();
}
/**
 * =====================================================
 * @author yinguoliang
 *
 */
public class RetrofitBaseTest {
	/**
	 * ********************test1************************
	 */
	public static void test1() throws IOException{
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.github.com")
//				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		QueryService service = retrofit.create(QueryService.class);
		Call<User> call = service.queryRepo();
		System.out.println(JSON.toJSONString(call.clone().execute().body(),true));
		System.out.println(JSON.toJSONString(call.execute().body(),true));
		
//		Response<User> res=service.queryUser();
//		U.print(JSON.toJSONString(res.body(),true));
	}
	/**
	 * *******************main**************************
	 */
	public static void main(String args[]) throws Exception{
		test1();
	}
}

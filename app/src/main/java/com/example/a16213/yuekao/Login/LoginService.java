package com.example.a16213.yuekao.Login;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> getRegister(@Field("username") String username,@Field("password")String password,@Field("repassword")String repassword);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> getLogin(@Field("username") String username,@Field("password")String password);

    @GET("json")
    Call<logout> getlogout();
}

package com.example.a16213.yuekao.Fragment.fragment1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface fgService1 {

    @GET("json")
    Call<bannerBean> getData();



    @GET("json")
    Call<fgBean1> getMessage();

}

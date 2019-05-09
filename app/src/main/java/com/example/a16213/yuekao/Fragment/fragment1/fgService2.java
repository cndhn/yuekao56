package com.example.a16213.yuekao.Fragment.fragment1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface fgService2 {

    @GET("json")
    Call<fgBean2> getData();



}

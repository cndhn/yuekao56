package com.example.a16213.yuekao;

import com.example.a16213.yuekao.Fragment.fragment1.fgBean2;
import com.example.a16213.yuekao.Fragment.fragment1.fgBean3;

import retrofit2.Call;
import retrofit2.http.GET;

public interface fgService3 {

    @GET("json")
    Call<fgBean3> getData();



}

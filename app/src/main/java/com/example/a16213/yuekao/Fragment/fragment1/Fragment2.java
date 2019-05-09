package com.example.a16213.yuekao.Fragment.fragment1;


import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.a16213.yuekao.R;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    RecyclerView recyclerView;
    fgRecycAdapter2 adapter1;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        recyclerView = view.findViewById(R.id.recyc);
        RecycMessage();
        return view;
    }


    public void RecycMessage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/tree/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fgService2 service2 = retrofit.create(fgService2.class);
        Call <fgBean2> call = service2.getData();
        call.enqueue(new Callback <fgBean2>() {
            @Override
            public void onResponse(Call <fgBean2> call, Response <fgBean2> response) {
                List <fgBean2.DataBean.ChildrenBean> datas = response.body().getData().get(0).getChildren();
                List <fgBean2.DataBean> data = response.body().getData();
                adapter1 = new fgRecycAdapter2();
                adapter1.refresh(datas);
                adapter1.refresh2(data);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call <fgBean2> call, Throwable t) {

            }
        });


    }

}

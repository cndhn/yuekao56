package com.example.a16213.yuekao.Fragment.fragment1;


import android.content.Context;
import android.os.Bundle;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    Banner banner;

    List<String> imglist;
    List<String> strlist;
    fgRecycAdapter1 adapter1;
    RecyclerView recyclerView;
    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);
        banner = view.findViewById(R.id.banner);
        recyclerView = view.findViewById(R.id.recyc);
        BannerMessage();
        RecycMessage();
        return view;
    }


    public void RecycMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/article/list/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fgService1 service1 = retrofit.create(fgService1.class);
        Call <fgBean1> message = service1.getMessage();
        message.enqueue(new Callback <fgBean1>() {
            @Override
            public void onResponse(Call <fgBean1> call, Response <fgBean1> response) {
                List <fgBean1.DataBean.DatasBean> datas = response.body().getData().getDatas();
                adapter1 = new fgRecycAdapter1();
                adapter1.refresh(datas);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call <fgBean1> call, Throwable t) {

            }
        });

    }

    public void BannerMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/banner/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fgService1 service1 = retrofit.create(fgService1.class);
        final Call <bannerBean> data = service1.getData();
        data.enqueue(new Callback <bannerBean>() {
            @Override
            public void onResponse(Call <bannerBean> call, Response<bannerBean> response) {
                List <bannerBean.DataBean> data1 = response.body().getData();
                imglist = new ArrayList <>();
                strlist = new ArrayList <>();
                for (int i=0;i<data1.size();i++){
                    imglist.add(data1.get(i).getImagePath());
                    strlist.add(data1.get(i).getTitle());
                }
                Log.i("我",imglist.size()+"=="+strlist.size());
                banner.setDelayTime(1000);
                banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                banner.setImages(imglist);
                banner.setBannerTitles(strlist);
                banner.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                banner.start();
            }

            @Override
            public void onFailure(Call <bannerBean> call, Throwable t) {

            }
        });
    }



}

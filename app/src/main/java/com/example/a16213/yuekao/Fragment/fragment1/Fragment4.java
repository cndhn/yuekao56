package com.example.a16213.yuekao.Fragment.fragment1;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a16213.yuekao.R;
import com.google.android.flexbox.FlexboxLayout;

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
public class Fragment4 extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    fg4pagerAdapter adapter;
    List<Fragment> frglist;
    List<String> strlist;
    fg4pagerAdapter pagerAdapter;

    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment2, container, false);
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.vp);
        RecycMessage();

        return view;
    }


    public void RecycMessage(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/article/list/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fgService1 service1 = retrofit.create(fgService1.class);
        Call<fgBean1> message = service1.getMessage();
        message.enqueue(new Callback<fgBean1>() {
            @Override
            public void onResponse(Call <fgBean1> call, Response<fgBean1> response) {
                List<fgBean1.DataBean.DatasBean> datas = response.body().getData().getDatas();
                pagerAdapter = new fg4pagerAdapter(getFragmentManager(),frglist,strlist);
                for (int i=0;i<datas.size();i++){
                    tabLayout.addTab(new TabLayout.Tab().setText("w"));
                }
                viewPager.setAdapter(pagerAdapter);
                tabLayout.setupWithViewPager(viewPager);



            }

            @Override
            public void onFailure(Call <fgBean1> call, Throwable t) {

            }
        });

    }



}

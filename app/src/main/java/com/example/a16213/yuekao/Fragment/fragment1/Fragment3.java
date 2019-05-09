package com.example.a16213.yuekao.Fragment.fragment1;


import android.os.Bundle;
import android.print.PrintJob;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a16213.yuekao.R;
import com.example.a16213.yuekao.fgService3;

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
public class Fragment3 extends Fragment{

    RecyclerView recyclerView;
    List<String> strlist;
    fgRecycAdapter3 adapter1;

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment3, container, false);
        recyclerView = view.findViewById(R.id.recyc1);
        RecycMessage();
        return view;
    }


    public void RecycMessage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/navi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fgService3 service3 = retrofit.create(fgService3.class);
        Call <fgBean3> message = service3.getData();
        message.enqueue(new Callback <fgBean3>() {
            @Override
            public void onResponse(Call <fgBean3> call, Response <fgBean3> response) {
                strlist = new ArrayList <>();
                List <fgBean3.DataBean> data = response.body().getData();
                for (int i = 0;i<data.size();i++){
                    strlist.add(data.get(i).getName());
                }
                Log.i("我",data.size()+"");
                adapter1 = new fgRecycAdapter3();
                adapter1.refresh(strlist);
                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call <fgBean3> call, Throwable t) {

            }
        });
    }



}

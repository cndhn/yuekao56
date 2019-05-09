package com.example.a16213.yuekao.Fragment.fragment1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a16213.yuekao.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class fgRecycAdapter3 extends RecyclerView.Adapter<fgRecycAdapter3.MyHolder>{


    List<String> list2 = new ArrayList <>();
    ItemClick itemClick;



    public void refresh(List<String> list2){
        this.list2.addAll(list2);
        notifyDataSetChanged();
    }

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fg3_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.textView1.setText(list2.get(i));
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.iclick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView1;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.str);


        }
    }

    interface  ItemClick{
        void iclick(int i);
    }

}

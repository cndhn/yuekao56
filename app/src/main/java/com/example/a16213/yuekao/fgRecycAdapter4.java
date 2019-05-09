package com.example.a16213.yuekao;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a16213.yuekao.Fragment.fragment1.fgBean1;

import java.util.ArrayList;
import java.util.List;

public class fgRecycAdapter4 extends RecyclerView.Adapter<fgRecycAdapter4.MyHolder>{

    List<fgBean1.DataBean.DatasBean> list = new ArrayList <>();


    public void refresh(List<fgBean1.DataBean.DatasBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fg1_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.textView1.setText(list.get(i).getAuthor());
        myHolder.textView2.setText(list.get(i).getSuperChapterName());
        myHolder.textView3.setText(list.get(i).getEnvelopePic());
        myHolder.textView4.setText(list.get(i).getTitle());
        myHolder.textView5.setText(list.get(i).getNiceDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.writer);
            textView2 = itemView.findViewById(R.id.yc);
            textView3 = itemView.findViewById(R.id.bk);
            textView4 = itemView.findViewById(R.id.title);
            textView5 = itemView.findViewById(R.id.riqi);
        }
    }

}

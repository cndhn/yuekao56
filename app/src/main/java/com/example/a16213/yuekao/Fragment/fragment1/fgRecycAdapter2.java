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

public class fgRecycAdapter2 extends RecyclerView.Adapter<fgRecycAdapter2.MyHolder>{

    List<fgBean2.DataBean.ChildrenBean> list = new ArrayList <>();
    List<fgBean2.DataBean> list2 = new ArrayList <>();



    public void refresh(List<fgBean2.DataBean.ChildrenBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void refresh2(List<fgBean2.DataBean> list2){
        this.list2.addAll(list2);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fg2_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.textView1.setText(list2.get(i).getName());
        String str = new String();
        for (int j = 0;j<list2.get(i).getChildren().size();j++){
            str += list2.get(i).getChildren().get(j).getName()+",";
           }
        myHolder.textView2.setText(str);
    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView textView1;
        TextView textView2;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.title1);
            textView2 = itemView.findViewById(R.id.message1);

        }
    }

}

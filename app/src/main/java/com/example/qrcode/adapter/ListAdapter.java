package com.example.qrcode.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qrcode.R;
import com.example.qrcode.activity.DetailActivity;
import com.example.qrcode.entity.Info;


import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<Info> infoList;
    //页面跳转时需要
    private Context context;
    //定义一个内部类
    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsView=itemView;
            name = itemView.findViewById(R.id.name);
        }
    }


    public ListAdapter(List<Info> infos,Context context) {
        this.infoList = infos;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);

        final  ViewHolder holder=new ViewHolder(view);
        holder.newsView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Info info = infoList.get(position);

                System.out.println("点我啦");
                System.out.println(info);

                //向详细页面传递该新闻的id
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id",info.getId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        Info info = infoList.get(position);
        holder.name.setText(info.getClientname());

    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }
}

package com.example.ryan.fzyapp.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ryan.fzyapp.R;
import com.example.ryan.fzyapp.bean.MeiZhiBean;

import java.util.List;

/**
 * Created by ryan on 18-10-19.
 */

public class MeiZhiRecyclerView extends RecyclerView.Adapter<MeiZhiRecyclerView.MeiZhiHolder> {
    private Context context;
    private List<MeiZhiBean.ResultsBean> list;

    public MeiZhiRecyclerView(Context context, List<MeiZhiBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MeiZhiRecyclerView.MeiZhiHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.meizhi_item,parent,false);
        MeiZhiHolder holder = new MeiZhiHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MeiZhiRecyclerView.MeiZhiHolder holder, int position) {

        String meizhi_url = list.get(position).getUrl();
        Log.d("Franck", "meizhi_url: " + meizhi_url);
        Glide.with(context)
                .load(meizhi_url)
                .asBitmap()
                .placeholder(R.drawable.dengdai)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MeiZhiHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MeiZhiHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}

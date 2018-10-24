package com.example.ryan.fzyapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ryan.fzyapp.R;
import com.example.ryan.fzyapp.bean.AndroidIosBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 18-10-22.
 */

public class AndroidRecyclerView extends RecyclerView.Adapter<AndroidRecyclerView.AndroidHolder> {

    private Context context;
    private List<AndroidIosBean.ResultsBean> list;
    private static final String TAG = "AndroidRecyclerView";
    private String image_url = null;

    public AndroidRecyclerView(Context context, List<AndroidIosBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public AndroidRecyclerView.AndroidHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.android_item,parent,false);
        AndroidHolder holder = new AndroidHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AndroidRecyclerView.AndroidHolder holder, int position) {
        String text = list.get(position).getDesc();


        if (list.get(position).getImages()!=null && list.get(position).getImages().size() != 0){
            image_url = list.get(position).getImages().get(0);
            Glide.with(context).load(image_url)
                    .asBitmap()
                    .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(holder.image);
        }else {
            Glide.with(context).load(R.drawable.error)
                    .asBitmap()
                    .error(R.drawable.error)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .centerCrop()
                    .into(holder.image);
        }




        holder.text.setText(text);

        Log.d(TAG, "image_url: " + image_url + " text :" + text);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class AndroidHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView text;

        public AndroidHolder(View itemView) {
            super(itemView);
             image = itemView.findViewById(R.id.android_image);
             text = itemView.findViewById(R.id.android_text);
        }
    }
}

package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.popularmoviesapp.model.Video;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosHolder>{

    private String path;
    private List<Video> videos;
    private int row_layout;
    private Context context;

    public VideosAdapter(List<Video> videos, int row_layout, Context context) {
        this.videos = videos;
        this.row_layout = row_layout;
        this.context = context;
    }

    @Override
    public VideosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false);
        return new VideosHolder(v);
    }

    @Override
    public void onBindViewHolder(VideosHolder holder, int position) {
        path = "https://img.youtube.com/vi/" + videos.get(position).getKey() + "/mqdefault.jpg";
        Glide.with(context).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .crossFade()
                .into(holder.backDrop);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if(isLongClick){

                }
                else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}

package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appbusters.robinkamboj.popularmoviesapp.R;

public class VideosHolder extends RecyclerView.ViewHolder{

    public ImageView backDrop;
    public RelativeLayout clickView;

    public VideosHolder(View itemView) {
        super(itemView);
        backDrop = (ImageView) itemView.findViewById(R.id.back_drop);
        clickView = (RelativeLayout) itemView.findViewById(R.id.click_view);
    }
}

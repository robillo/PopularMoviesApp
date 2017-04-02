package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appbusters.robinkamboj.popularmoviesapp.R;

public class VideosHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public ImageView backDrop;
    public RelativeLayout clickView;
    private ItemClickListener clickListener;
    private Context context;

    public VideosHolder(View itemView) {
        super(itemView);
        backDrop = (ImageView) itemView.findViewById(R.id.back_drop);
        clickView = (RelativeLayout) itemView.findViewById(R.id.click_view);
        context = itemView.getContext();

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(view, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onClick(view, getAdapterPosition(), true);
        return false;
    }

    public void video_intent(String video_id){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video_id));
        context.startActivity(i);
    }
}

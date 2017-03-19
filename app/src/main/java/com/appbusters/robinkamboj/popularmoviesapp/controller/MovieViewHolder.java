package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    LinearLayout moviesLayout;
    TextView movieTitle;
    ImageView poster;

    public MovieViewHolder(View itemView) {
        super(itemView);
        moviesLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
        movieTitle = (TextView) itemView.findViewById(R.id.title);
        poster = (ImageView) itemView.findViewById(R.id.poster);
    }
}

package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.view.activities.DetailActivity;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    public LinearLayout moviesLayout;
    public TextView movieTitle;
    public ImageView poster;
    private Context context;

    public MovieViewHolder(View itemView) {
        super(itemView);
        moviesLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
        movieTitle = (TextView) itemView.findViewById(R.id.title);
        poster = (ImageView) itemView.findViewById(R.id.poster);
        context = itemView.getContext();
    }

    public void DetailIntent(Movie movie){
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra("title", movie.getTitle());
        i.putExtra("", movie.getPoster_path());
        i.putExtra("", movie.isAdult());
        i.putExtra("", movie.getBackdrop_path());
        i.putExtra("", movie.getOriginal_language());
        i.putExtra("", movie.getPopularity());
        i.putExtra("", movie.getRelease_date());
        i.putExtra("", movie.getVote_count());
        i.putExtra("", movie.getVote_average());
        i.putExtra("", movie.isVideo());
        context.startActivity(i);
    }
}

package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.view.activities.DetailActivity;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public RelativeLayout moviesLayout;
    public TextView movieTitle;
    public ImageView poster;
    private ItemClickListener clickListener;
    private Context context;

    public MovieViewHolder(View itemView) {
        super(itemView);
        moviesLayout = (RelativeLayout) itemView.findViewById(R.id.movies_layout);
        movieTitle = (TextView) itemView.findViewById(R.id.title);
        poster = (ImageView) itemView.findViewById(R.id.poster);
        context = itemView.getContext();

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void DetailIntent(String title, String poster_path, String is_adult, String backdrop_path, String original_language, String popularity, String release_date, String vote_count, String vote_average, String is_video){
        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra("title", title);
        i.putExtra("poster_path", poster_path);
        i.putExtra("is_adult", is_adult);
        i.putExtra("backdrop_path", backdrop_path);
        i.putExtra("original_language", original_language);
        i.putExtra("popularity", popularity);
        i.putExtra("release_date", release_date);
        i.putExtra("vote_count", vote_count);
        i.putExtra("vote_average", vote_average);
        i.putExtra("is_video", is_video);
        context.startActivity(i);
    }

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View view) {
        clickListener.onClick(itemView, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View view) {
        clickListener.onClick(itemView, getAdapterPosition(), true);
        return false;
    }
}

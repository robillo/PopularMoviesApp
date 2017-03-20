package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovieViewHolder holder, int position) {
        String path = movies.get(position).getPoster_path();
        holder.movieTitle.setText(movies.get(position).getTitle());
        Glide.with(context).load("http://image.tmdb.org/t/p/w185" + path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .fitCenter()
                .placeholder(R.drawable.placeholder)
                .into(holder.poster);
        Log.e("POSTER PATH: ", path);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if(isLongClick){
                    holder.DetailIntent();
                    Log.e("MOVIE NAME: ", movies.get(position).getTitle());
                }
                else {
                    Log.e("VOTE AVERAGE", String.valueOf(movies.get(position).getVote_average()));
                    Log.e("VOTE COUNT", String.valueOf(movies.get(position).getVote_count()));
                    holder.DetailIntent();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

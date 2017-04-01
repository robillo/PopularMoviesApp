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

import java.util.Collections;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private List<Movie> movies = Collections.emptyList();
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
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.poster);
        Log.e("POSTER PATH: ", path);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View v, int position, boolean isLongClick) {
                if(isLongClick){
                    holder.DetailIntent(movies.get(position).getTitle(), movies.get(position).getPoster_path(), String.valueOf(movies.get(position).isAdult()), movies.get(position).getBackdrop_path(), movies.get(position).getOriginal_language(), String.valueOf(movies.get(position).getPopularity()), movies.get(position).getRelease_date(), String.valueOf(movies.get(position).getVote_count()), String.valueOf(movies.get(position).getVote_average()), String.valueOf(movies.get(position).isVideo()), movies.get(position).getOverview(), movies.get(position).getId());
                    Log.e("MOVIE NAME: ", movies.get(position).getTitle());
                }
                else {
                    Log.e("VOTE AVERAGE", String.valueOf(movies.get(position).getVote_average()));
                    Log.e("VOTE COUNT", String.valueOf(movies.get(position).getVote_count()));
                    holder.DetailIntent(movies.get(position).getTitle(), movies.get(position).getPoster_path(), String.valueOf(movies.get(position).isAdult()), movies.get(position).getBackdrop_path(), movies.get(position).getOriginal_language(), String.valueOf(movies.get(position).getPopularity()), movies.get(position).getRelease_date(), String.valueOf(movies.get(position).getVote_count()), String.valueOf(movies.get(position).getVote_average()), String.valueOf(movies.get(position).isVideo()), movies.get(position).getOverview(), movies.get(position).getId());
                }
            }
        });
    }

    public void Clear(){
        movies.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Movie> list) {
        movies.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}

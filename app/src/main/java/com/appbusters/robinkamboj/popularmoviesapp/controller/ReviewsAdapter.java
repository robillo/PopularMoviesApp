package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsHolder>{

    private String title, description, reviewee, path;
    private int row_layout;
    private Context context;

    public ReviewsAdapter(String title, String description, String reviewee, String path, int row_layout, Context context) {
        this.title = title;
        this.description = description;
        this.reviewee = reviewee;
        this.path = path;
        this.row_layout = row_layout;
        this.context = context;
    }

    @Override
    public ReviewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(row_layout, parent, false);
        return new ReviewsHolder(v);
    }

    @Override
    public void onBindViewHolder(ReviewsHolder holder, int position) {
        holder.review_title.setText(title);
        holder.review_description.setText(description);
        holder.review_reviewee.setText(reviewee);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

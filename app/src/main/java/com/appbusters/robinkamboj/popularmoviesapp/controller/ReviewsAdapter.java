package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.popularmoviesapp.model.Review;

import java.util.Collections;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsHolder>{

    private List<Review> reviews;
    private int row_layout;
    private Context context;

    public ReviewsAdapter(List<Review> reviews, int row_layout, Context context) {
        this.reviews = reviews;
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
        holder.review_description.setText(reviews.get(position).getContent());
        holder.review_reviewee.setText(reviews.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }
}

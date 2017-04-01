package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;

public class ReviewsHolder extends RecyclerView.ViewHolder{

    public TextView review_description, review_reviewee;

    public ReviewsHolder(View itemView) {
        super(itemView);
        review_description = (TextView) itemView.findViewById(R.id.description);
        review_reviewee = (TextView) itemView.findViewById(R.id.reviewee);
    }
}

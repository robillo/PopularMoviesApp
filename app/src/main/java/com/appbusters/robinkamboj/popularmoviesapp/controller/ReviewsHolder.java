package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;

public class ReviewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView review_description, review_reviewee;
    private ItemClickListener clickListener;

    public ReviewsHolder(View itemView) {
        super(itemView);
        review_description = (TextView) itemView.findViewById(R.id.description);
        review_reviewee = (TextView) itemView.findViewById(R.id.reviewee);

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
}

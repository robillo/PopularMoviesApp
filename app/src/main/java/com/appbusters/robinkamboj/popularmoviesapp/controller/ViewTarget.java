package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class ViewTarget extends ViewTarget2<GlideDrawable> {

    public ViewTarget(ViewGroup view) {
        super(view);
    }

    @Override
    protected void setResource(GlideDrawable resource) {
        view.setBackground(resource);

    }
}
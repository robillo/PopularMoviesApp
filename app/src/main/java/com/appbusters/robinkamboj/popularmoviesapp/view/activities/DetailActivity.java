package com.appbusters.robinkamboj.popularmoviesapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.controller.ViewTarget;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailActivity extends AppCompatActivity {

    private String title, poster_path, backdrop_path, vote_average, is_video, is_adult, vote_count, release_date, popularity, original_language, overview, id;
    private CollapsingToolbarLayout toolbar_layout;

    private ImageView poster;
    private TextView movie_title, movie_rating, movie_release_date, movie_overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poster = (ImageView) findViewById(R.id.poster);
        movie_title = (TextView) findViewById(R.id.title);
        movie_rating = (TextView) findViewById(R.id.rating);
        movie_release_date = (TextView) findViewById(R.id.release_date);
        movie_overview = (TextView) findViewById(R.id.overview);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Intent i = getIntent();
        title = i.getStringExtra("title");
        poster_path = i.getStringExtra("poster_path");
        backdrop_path = i.getStringExtra("backdrop_path");
        vote_average = i.getStringExtra("vote_average");
        is_adult = i.getStringExtra("is_adult");
        is_video = i.getStringExtra("is_video");
        vote_count = i.getStringExtra("vote_count");
        release_date = i.getStringExtra("release_date");
        popularity = i.getStringExtra("popularity");
        original_language = i.getStringExtra("original_language");
        overview = i.getStringExtra("overview");
        id = i.getStringExtra("id");

        getSupportActionBar().setTitle(title);

        movie_title.setText(title);
        movie_rating.setText(vote_average);
        movie_release_date.setText(release_date);
        movie_overview.setText(overview);

        Glide.with(this).load("http://image.tmdb.org/t/p/w185" + poster_path)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(poster);

        Log.e("DETAILS", title + "\n" + poster_path + "\n" + backdrop_path + "\n" + vote_average + "\n" + is_adult + "\n" + is_video
                + "\n" + vote_count + "\n" + release_date + "\n" + popularity + "\n" + id + "\n" + original_language);

        ViewTarget view_target = new ViewTarget(toolbar_layout);

        Glide.with(this).load("http://image.tmdb.org/t/p/w185" + backdrop_path)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view_target);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

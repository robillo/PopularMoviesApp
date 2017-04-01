package com.appbusters.robinkamboj.popularmoviesapp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.controller.ReviewsAdapter;
import com.appbusters.robinkamboj.popularmoviesapp.controller.ViewTarget;
import com.appbusters.robinkamboj.popularmoviesapp.model.Review;
import com.appbusters.robinkamboj.popularmoviesapp.model.ReviewsResponse;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiClient;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String API_KEY = "13befb0c6409e8c61c5e9ec4265a1d1c";
    private ApiInterface apiService;
    private List<Review> reviews;
    private RecyclerView reviews_rv;
    private CardView reviews_card;
    private String title, poster_path, backdrop_path, vote_average, is_video, is_adult, vote_count, release_date, popularity, original_language, overview;
    private int id;
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

        reviews_card = (CardView) findViewById(R.id.reviews_card);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        reviews_rv = (RecyclerView) findViewById(R.id.reviews);
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
        id = i.getIntExtra("id", 550);

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

        reviews = Collections.emptyList();
        callReviews();
    }

    private void callReviews(){
        Call<ReviewsResponse> call = apiService.searchMovieReviews(id, API_KEY);
        if(call!=null){
            call.enqueue(new Callback<ReviewsResponse>() {
                @Override
                public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                    reviews = new ArrayList<Review>();
                    reviews = response.body().getResults();
                    for(Review singleReview : reviews){
                        Log.e("Review", singleReview.getContent());
//                        reviews.add(singleReview);
                    }
                    if(reviews.size()>0){
                        reviews_card.setVisibility(View.VISIBLE);
                        reviews_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        ReviewsAdapter adapter = new ReviewsAdapter(reviews, R.layout.reviews_layout, getApplicationContext());
                        Log.e("Size", " " + adapter.getItemCount());
                        reviews_rv.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ReviewsResponse> call, Throwable t) {

                }
            });
        }
    }
}

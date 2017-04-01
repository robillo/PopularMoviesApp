package com.appbusters.robinkamboj.popularmoviesapp.rest;

import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.model.MoviesResponse;
import com.appbusters.robinkamboj.popularmoviesapp.model.ReviewsResponse;
import com.appbusters.robinkamboj.popularmoviesapp.model.VideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    //First Page Results

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie?sort_by=vote_count.desc")
    Call<MoviesResponse> getMostRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getMostPopularMovies(@Query("api_key") String apiKey);

    //Next Page Results

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("discover/movie?sort_by=vote_count.desc")
    Call<MoviesResponse> getMostRatedMovies(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("movie/popular")
    Call<MoviesResponse> getMostPopularMovies(@Query("api_key") String apiKey, @Query("page") int page);

    //Search Movie By ID

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    //Search Movie By Name

    @GET("search/movie?query=")
    Call<MoviesResponse> searchMovieDetails(String name, @Query("api_key") String apiKey);

    //Search Movie Videos

    @GET("movie/{id}/videos")
    Call<VideosResponse> searchMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    //Search Movie Reviews

    @GET("movie/{id}/reviews")
    Call<ReviewsResponse> searchMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

}

package com.appbusters.robinkamboj.popularmoviesapp.rest;

import com.appbusters.robinkamboj.popularmoviesapp.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie?sort_by=vote_count.desc")
    Call<MoviesResponse> getMostRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MoviesResponse> getMostPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("search/movie?query=")
    Call<MoviesResponse> searchMovieDetails(String name, @Query("api_key") String apiKey);

}

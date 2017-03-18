package com.appbusters.robinkamboj.popularmoviesapp.view.fragments;


import android.media.ImageReader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.controller.MoviesAdapter;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.model.MoviesResponse;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiClient;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiInterface;
import com.appbusters.robinkamboj.popularmoviesapp.view.activities.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllMoviesFragment extends Fragment {


    private static final String TAG = AllMoviesFragment.class.getSimpleName();
    private static final String API_KEY = "13befb0c6409e8c61c5e9ec4265a1d1c";
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private LinearLayout alternate_layout;

    public AllMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_movies, container, false);

        alternate_layout = (LinearLayout) v.findViewById(R.id.alternate_layout);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                if(adapter.getItemCount()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    alternate_layout.setVisibility(View.INVISIBLE);
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        return v;
    }

}

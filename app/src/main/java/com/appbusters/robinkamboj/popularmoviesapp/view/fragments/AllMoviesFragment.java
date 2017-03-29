package com.appbusters.robinkamboj.popularmoviesapp.view.fragments;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.controller.MoviesAdapter;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.model.MoviesResponse;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiClient;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMoviesFragment extends Fragment{

    private static final String TAG = AllMoviesFragment.class.getSimpleName();
    private static final String API_KEY = "13befb0c6409e8c61c5e9ec4265a1d1c";
    private CharSequence items[] = {"Highest Rated", "Most Popular", "Most Rated"};
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private LinearLayout alternate_layout;
    private ApiInterface apiService;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout refreshLayout;
    private static int which_filter = 0;

    public AllMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_movies, container, false);

        setHasOptionsMenu(true);

//        setRetainInstance(true);

        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        alternate_layout = (LinearLayout) v.findViewById(R.id.alternate_layout);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        }
        else if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
                if(movies.size()>0){
                    adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                    if(adapter.getItemCount()>0){
                        recyclerView.setVisibility(View.VISIBLE);
                        alternate_layout.setVisibility(View.INVISIBLE);
                    }
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("ON REFRESH CALLED", "onRefresh called from SwipeRefreshLayout");
                Handler handler = new Handler();
                Handler handler1 = new Handler();
                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        switch (which_filter){
                            case 0:{
                                Log.e("which?", "Highest Rated");
                                apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
                                call.enqueue(new Callback<MoviesResponse>() {
                                    @Override
                                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                        List<Movie> movies = response.body().getResults();
                                        adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                        if(adapter.getItemCount()>0){
                                            recyclerView.setVisibility(View.VISIBLE);
                                            recyclerView.setAdapter(adapter);
                                            alternate_layout.setVisibility(View.INVISIBLE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                    }
                                });
                                break;
                            }
                            case 1:{
                                Log.e("which?", "Most Popular");
                                apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<MoviesResponse> call = apiService.getMostPopularMovies(API_KEY);
                                call.enqueue(new Callback<MoviesResponse>() {
                                    @Override
                                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                        if(response.body()!=null){
                                            List<Movie> movies = response.body().getResults();
                                            adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                            if(adapter.getItemCount()>0){
                                                recyclerView.setVisibility(View.VISIBLE);
                                                recyclerView.setAdapter(adapter);
                                                alternate_layout.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                    }
                                });
                                break;
                            }
                            case 2:{
                                Log.e("which?", "Most Rated");
                                apiService = ApiClient.getClient().create(ApiInterface.class);
                                Call<MoviesResponse> call = apiService.getMostRatedMovies(API_KEY);
                                call.enqueue(new Callback<MoviesResponse>() {
                                    @Override
                                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                        if(response.body()!=null){
                                            List<Movie> movies = response.body().getResults();
                                            adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                            if(adapter.getItemCount()>0){
                                                recyclerView.setVisibility(View.VISIBLE);
                                                recyclerView.setAdapter(adapter);
                                                alternate_layout.setVisibility(View.INVISIBLE);
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                    }
                                });
                                break;
                            }
                        }
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);

        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_search:{

                break;
            }
            case R.id.action_sort:{
                Log.e("CHECK CLICK", "CLICKED! YO!");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Sorting Option:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                switch (which){
                                    case 0:{
                                        which_filter = 0;
                                        Log.e("which?", "Highest Rated");
                                        apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
                                        call.enqueue(new Callback<MoviesResponse>() {
                                            @Override
                                            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                                List<Movie> movies = response.body().getResults();
                                                adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                                if(adapter.getItemCount()>0){
                                                    recyclerView.setVisibility(View.VISIBLE);
                                                    recyclerView.setAdapter(adapter);
                                                    alternate_layout.setVisibility(View.INVISIBLE);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                            }
                                        });
                                        break;
                                    }
                                    case 1:{
                                        which_filter = 1;
                                        Log.e("which?", "Most Popular");
                                        apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<MoviesResponse> call = apiService.getMostPopularMovies(API_KEY);
                                        call.enqueue(new Callback<MoviesResponse>() {
                                            @Override
                                            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                                if(response.body()!=null){
                                                    List<Movie> movies = response.body().getResults();
                                                    adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                                    if(adapter.getItemCount()>0){
                                                        recyclerView.setVisibility(View.VISIBLE);
                                                        recyclerView.setAdapter(adapter);
                                                        alternate_layout.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                            }
                                        });
                                        break;
                                    }
                                    case 2:{
                                        which_filter = 2;
                                        Log.e("which?", "Most Rated");
                                        apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<MoviesResponse> call = apiService.getMostRatedMovies(API_KEY);
                                        call.enqueue(new Callback<MoviesResponse>() {
                                            @Override
                                            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                                                if(response.body()!=null){
                                                    List<Movie> movies = response.body().getResults();
                                                    adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                                                    if(adapter.getItemCount()>0){
                                                        recyclerView.setVisibility(View.VISIBLE);
                                                        recyclerView.setAdapter(adapter);
                                                        alternate_layout.setVisibility(View.INVISIBLE);
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<MoviesResponse> call, Throwable t) {

                                            }
                                        });
                                        break;
                                    }
                                }
                            }
                        });
                builder.show();
                break;
            }
            case R.id.action_settings:{

                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("WHICH_FILTER", which_filter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            which_filter = savedInstanceState.getInt("WHICH_FILTER");
            Log.e("THE FILTER IS", " " + which_filter);
        }
        super.onActivityCreated(savedInstanceState);
    }
}

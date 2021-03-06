package com.appbusters.robinkamboj.popularmoviesapp.view.fragments;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import com.appbusters.robinkamboj.popularmoviesapp.controller.EndlessScrollListener;
import com.appbusters.robinkamboj.popularmoviesapp.controller.MoviesAdapter;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;
import com.appbusters.robinkamboj.popularmoviesapp.model.MoviesResponse;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiClient;
import com.appbusters.robinkamboj.popularmoviesapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMoviesFragment extends Fragment{

    private static final String API_KEY = "13befb0c6409e8c61c5e9ec4265a1d1c";
    private CharSequence items[] = {"Highest Rated", "Most Popular", "Most Rated"};
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private LinearLayout alternate_layout;
    private ApiInterface apiService;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout refreshLayout;
    private static int which_filter = 0;
    private int page_number = 1;
    private List<Movie> movies;

    public AllMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_movies, container, false);

        setHasOptionsMenu(true);

        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swiperefresh);
        alternate_layout = (LinearLayout) v.findViewById(R.id.alternate_layout);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        }
        else if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        }

        movies = new ArrayList<>();

        recyclerView.setLayoutManager(gridLayoutManager);

        callMovies(page_number);
        refresh();

        EndlessScrollListener scrollListener = new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page_number);
            }
        };

        recyclerView.addOnScrollListener(scrollListener);

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
//                Log.e("CHECK CLICK", "CLICKED! YO!");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Choose Sorting Option:")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                switch (which){
                                    case 0:{
                                        which_filter = 0;
                                        Log.e("which?", "Highest Rated");
                                        callMovies(1);
                                        break;
                                    }
                                    case 1:{
                                        which_filter = 1;
                                        Log.e("which?", "Most Popular");
                                        callMovies(1);
                                        break;
                                    }
                                    case 2:{
                                        which_filter = 2;
                                        Log.e("which?", "Most Rated");
                                        callMovies(1);
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
            case R.id.move_to_first:{
                recyclerView.getLayoutManager().scrollToPosition(1);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("WHICH_FILTER", which_filter);
        outState.putInt("WHICH_PAGE", page_number);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            which_filter = savedInstanceState.getInt("WHICH_FILTER");
            page_number = savedInstanceState.getInt("WHICH_PAGE");
//            Log.e("THE FILTER IS", " " + which_filter);
        }
        else {
            refresh();
        }
        super.onActivityCreated(savedInstanceState);
    }

    private void loadNextDataFromApi(int page) {

//        Log.e("LNDFA", "LOADING");

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = null;
        switch (which_filter){
            case 0:{
                call = apiService.getTopRatedMovies(API_KEY, page);
                break;
            }
            case 1:{
                call = apiService.getMostPopularMovies(API_KEY, page);
                break;
            }
            case 2:{
                call = apiService.getMostRatedMovies(API_KEY, page);
                break;
            }
        }
//        Log.e("CALL", "TRUE");
//        if (call != null) {
        if (call != null) {
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    for (Movie singleMovie : response.body().getResults()) {
                        movies.add(singleMovie);
//                        Log.e("Single Movie", singleMovie.getTitle());
                    }
//                    movies = response.body().getResults();
                    adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity());
//                    Log.e("Adapter", " " + adapter.getItemCount());
//                    adapter.notifyItemRangeInserted(21, 40);
//                    if(adapter.getItemCount()>0){
//                        recyclerView.setVisibility(View.VISIBLE);
//                        recyclerView.setAdapter(adapter);
                    recyclerView.getAdapter().notifyDataSetChanged();
                    page_number++;
//                        alternate_layout.setVisibility(View.INVISIBLE);
                    }
//                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {

                }
            });
        }
    }
//    }

    private void callMovies(int page){
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = null;
        switch (which_filter){
            case 0:{
                call = apiService.getTopRatedMovies(API_KEY, page);
                break;
            }
            case 1:{
                call = apiService.getMostPopularMovies(API_KEY, page);
                break;
            }
            case 2:{
                call = apiService.getMostRatedMovies(API_KEY, page);
                break;
            }
        }
        if (call != null) {
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    movies = response.body().getResults();
                    adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
                    if(adapter.getItemCount()>0){
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(adapter);
                        alternate_layout.setVisibility(View.INVISIBLE);
                        page_number++;
                    }
                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {

                }
            });
        }
    }

    private void refresh(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Log.e("ON REFRESH CALLED", "onRefresh called from SwipeRefreshLayout");
//                apiService = ApiClient.getClient().create(ApiInterface.class);
//                Call<MoviesResponse> call = null;
//                switch (which_filter){
//                    case 0:{
//                        call = apiService.getTopRatedMovies(API_KEY, page_number);
//                        break;
//                    }
//                    case 1:{
//                        call = apiService.getMostPopularMovies(API_KEY, page_number);
//                        break;
//                    }
//                    case 2:{
//                        call = apiService.getMostRatedMovies(API_KEY, page_number);
//                        break;
//                    }
//                }
//                if (call != null) {
//                    call.enqueue(new Callback<MoviesResponse>() {
//                        @Override
//                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//                            movies = response.body().getResults();
//                            adapter = new MoviesAdapter(movies, R.layout.row_layout, getActivity().getApplicationContext());
//                            if(adapter.getItemCount()>0){
//                                recyclerView.setVisibility(View.VISIBLE);
//                                recyclerView.setAdapter(adapter);
//                                alternate_layout.setVisibility(View.INVISIBLE);
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
//
//                        }
//                    });
//                }

                page_number=1;

                Handler handler = new Handler();
                Handler handler1 = new Handler();
                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        callMovies(page_number);
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });
    }
}

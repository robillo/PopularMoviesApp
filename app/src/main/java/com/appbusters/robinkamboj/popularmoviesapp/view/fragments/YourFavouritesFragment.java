package com.appbusters.robinkamboj.popularmoviesapp.view.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.appbusters.robinkamboj.popularmoviesapp.R;
import com.appbusters.robinkamboj.popularmoviesapp.controller.MoviesAdapter;
import com.appbusters.robinkamboj.popularmoviesapp.controller.MyDBHelper;
import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourFavouritesFragment extends Fragment {

    private MyDBHelper myDBHelper;
    private List<Movie> data;
    private int moviesCount;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private MoviesAdapter adapter;
    private LinearLayout alternateLayout;

    public YourFavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_your_favourites, container, false);

        alternateLayout = (LinearLayout) v.findViewById(R.id.alternate_layout);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        }
        else if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        }

        recyclerView.setLayoutManager(gridLayoutManager);

        myDBHelper = new MyDBHelper(getActivity());
        data = new ArrayList<>();
        data = myDBHelper.getAllMovies();
        moviesCount = myDBHelper.rowsCount();

        if(moviesCount>=1){
            adapter = new MoviesAdapter(data, R.layout.row_layout, getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            alternateLayout.setVisibility(View.INVISIBLE);
        }

        setHasOptionsMenu(true);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_detail, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

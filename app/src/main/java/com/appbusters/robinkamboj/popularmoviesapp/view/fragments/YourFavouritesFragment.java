package com.appbusters.robinkamboj.popularmoviesapp.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appbusters.robinkamboj.popularmoviesapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class YourFavouritesFragment extends Fragment {


    public YourFavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_favourites, container, false);
    }

}

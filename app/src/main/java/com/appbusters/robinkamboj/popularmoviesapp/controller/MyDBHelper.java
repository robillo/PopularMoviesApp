package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movies.db";
    private static final String TABLE_NAME = "FAVORITE_MOVIES";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String POSTER = "POSTER";
    private static final String BACKDROP = "BACKDROP";
    private static final String RATING = "RATING";
    private static final String RELEASE_DATE = "DATE";
    private static final String OVERVIEW = "OVERVIEW";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY, " + NAME + " TEXT, " + POSTER + " TEXT, " + BACKDROP + " TEXT, " + RATING + " TEXT, " + RELEASE_DATE + " TEXT, " + OVERVIEW + " OVERVIEW)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
package com.appbusters.robinkamboj.popularmoviesapp.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.appbusters.robinkamboj.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Favorites.db";
    private static final String TABLE_NAME = "FAVORITE_MOVIES";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String POSTER = "POSTER";
    private static final String BACKDROP = "BACKDROP";
    private static final String RATING = "RATING";
    private static final String RELEASE_DATE = "RELEASE";
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

    //CRUD OPERATIONS

    //create:
    public Boolean insertMovie(String title, String poster_path, String backdrop_path, String vote_average, String release_date, String overview, int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID + " ", id);
        contentValues.put(NAME + " ", title);
        contentValues.put(POSTER + " ","http://image.tmdb.org/t/p/w780"+ poster_path);
        contentValues.put(BACKDROP + " ","http://image.tmdb.org/t/p/w780"+ backdrop_path);
        contentValues.put(RATING + " ", vote_average);
        contentValues.put(RELEASE_DATE + " ", release_date);
        contentValues.put(OVERVIEW + " ", overview);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    //read:
    public Cursor getMovie(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + "WHERE " + ID + "=" + id, null);
        return cursor;
    }

    //update: updateMovie() not required.

    //delete:
    public Integer deleteMovie(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, ID + " =? ", new String[]{Integer.toString(id)});
    }

    public List<Movie> getAllMovies() {
        List<Movie> data = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor res =  sqLiteDatabase.rawQuery( "SELECT * FROM " + TABLE_NAME + " ", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            List<Integer> i = new List<Integer>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<Integer> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(@NonNull T[] ts) {
                    return null;
                }

                @Override
                public boolean add(Integer integer) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean addAll(@NonNull Collection<? extends Integer> collection) {
                    return false;
                }

                @Override
                public boolean addAll(int i, @NonNull Collection<? extends Integer> collection) {
                    return false;
                }

                @Override
                public boolean removeAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean retainAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public Integer get(int i) {
                    return null;
                }

                @Override
                public Integer set(int i, Integer integer) {
                    return null;
                }

                @Override
                public void add(int i, Integer integer) {

                }

                @Override
                public Integer remove(int i) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Integer> listIterator() {
                    return null;
                }

                @NonNull
                @Override
                public ListIterator<Integer> listIterator(int i) {
                    return null;
                }

                @NonNull
                @Override
                public List<Integer> subList(int i, int i1) {
                    return null;
                }
            };
            data.add(new Movie(res.getString(res.getColumnIndex(POSTER)), false, res.getString(res.getColumnIndex(OVERVIEW)), res.getString(res.getColumnIndex(RELEASE_DATE)), res.getInt(res.getColumnIndex(ID)), res.getString(res.getColumnIndex(NAME)), "null", res.getString(res.getColumnIndex(NAME)), res.getString(res.getColumnIndex(BACKDROP)), 0, 0, false, 0, i));
            res.moveToNext();
        }

        return data;
    }

    public int rowsCount(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, TABLE_NAME);
        return numRows;
    };

}
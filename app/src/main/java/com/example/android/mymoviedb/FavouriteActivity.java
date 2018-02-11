package com.example.android.mymoviedb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
ArrayList<Integer> movieId;
    ArrayList<String> favTitles;
    ArrayList<String> favUrl;
    SearchAdapter adapter;
    RecyclerView favList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
favList = (RecyclerView) findViewById(R.id.favList);
        db openHelper = db.getInstance(getApplicationContext());
        SQLiteDatabase db1 = openHelper.getReadableDatabase();

        Cursor cursor = db1.query(Contract.FAV_TABLE,null,null,null,null,null,null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(Contract.MOVIE_ID));
            Log.i("movieid",id+"");
            String mvFavTitle = cursor.getString(cursor.getColumnIndex(Contract.MOVIE_TITLE));
            String PosterUrl = cursor.getString(cursor.getColumnIndex(Contract.MOVIE_URL));
            movieId.add(id);
            favTitles.add(mvFavTitle);
            favUrl.add(PosterUrl);
            Log.i("idmovie",movieId.size()+"");

        }

        cursor.close();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        favList.setLayoutManager(layoutManager);


        adapter = new SearchAdapter(this, favTitles, favUrl, movieId);
        favList.setAdapter(adapter);
    }
}

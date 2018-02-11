package com.example.android.mymoviedb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Result> popularList = new ArrayList<>();
    ArrayList<Integer> movieId = new ArrayList<>();


ArrayList<String> favTitles;
    SearchView searchView;
    MovieFrame movieframe = null;
    ImageButton fav;
   TvFrame tvframe =  null;
    static int loadMovieListOnce = 1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                {

//loadMovieListOnce = 0;
                    movieframe= new MovieFrame();
                    FragmentManager fm= getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
//ft.detach(tvframe);

                    ft.replace(R.id.content, movieframe);
                    ft.commit();

                    Log.i("a","b");
//                    tvframe= new TvFrame();
////loadMovieListOnce = 0;
//                    FragmentManager fm2= getSupportFragmentManager();
//                    FragmentTransaction ft2 = fm2.beginTransaction();
////ft2.detach(movieframe);
//                    ft2.add(R.id.content,tvframe);
//                    ft2.commit();
//
//                    Log.i("abc","b");


                    return true;}
                case R.id.navigation_dashboard:


                 tvframe= new TvFrame();
//loadMovieListOnce = 0;
                    FragmentManager fm2= getSupportFragmentManager();
                    FragmentTransaction ft2 = fm2.beginTransaction();
ft2.detach(movieframe);
                    ft2.replace(R.id.content,tvframe);
                    ft2.commit();

                      Log.i("abc","b");
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fav = (ImageButton) findViewById(R.id.imageButton);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Intent i = new Intent(MainActivity.this,FavouriteActivity.class);
                MainActivity.this.startActivity(i);

            }
        });
searchView =(SearchView)findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                Log.i("search","test");
                Intent i  = new Intent(MainActivity.this,SearchActivity.class);
                i.putExtra("query",query);
                MainActivity.this.startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
return false;
            } });
        FrameLayout container =(FrameLayout) findViewById(R.id.content);
        FragmentManager fragmentManager = getSupportFragmentManager();
        movieframe= new MovieFrame();
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
//ft.detach(tvframe);

        ft.add(R.id.content, movieframe);
        ft.commit();





        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

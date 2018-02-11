package com.example.android.mymoviedb;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends YouTubeBaseActivity {
TextView title,description , star_cast,release_date;
    YouTubePlayerView youTubePlayerView;
    ImageView img;
    String movieURL;
    String vdeoUrl;
    String titlePublic;
    ImageButton favButton;
    YouTubePlayer.OnInitializedListener youTubePlayerOnInitialised;
    ScrollView sv ;
    public  int mov_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);
        release_date = (TextView) findViewById(R.id.release_date);
        star_cast = (TextView) findViewById(R.id.star_cast);
        img = (ImageView) findViewById(R.id.imageView2);
favButton = (ImageButton) findViewById(R.id.favButton);
        description.setVisibility(View.INVISIBLE);
        release_date.setVisibility(View.INVISIBLE);
        star_cast.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        final com.wang.avi.AVLoadingIndicatorView avi = (com.wang.avi.AVLoadingIndicatorView )findViewById(R.id.avi);

        avi.show();

        Bundle extra = getIntent().getExtras();
      mov_id = extra.getInt("id",0);


            // or avi.smoothToShow();



           Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieInterface service = retrofit.create(MovieInterface.class);
        Call<Mymovie> call = service.getMovieIntent(mov_id+"","09d81dbea54787a5742e5f0adfc8ae0e");
        call.enqueue(new Callback<Mymovie>() {
            @Override
            public void onResponse(Call<Mymovie> call, Response<Mymovie> response) {
                Mymovie mv =response.body();
                titlePublic =mv.getTitle();
movieURL = mv.getPosterPath();
                title.setText(mv.getTitle());
                description.setText("MOVIE OVERVIEW :   \n  "+mv.getOverview());
                release_date .setText("RELEASE DATE: "+mv.getReleaseDate());
                star_cast.setText( mv.getAdult()+"");
                String urlString = "https://image.tmdb.org/t/p/w500/"+mv.getPosterPath();
                Picasso.with(MovieActivity.this)
                        .load(urlString)
                        .placeholder(R.drawable.ic_home_black_24dp) // optional
                        .error(R.drawable.ic_notifications_black_24dp)         // optional
                        .into(img);
avi.hide();
                description.setVisibility(View.VISIBLE);
                release_date.setVisibility(View.VISIBLE);
                star_cast.setVisibility(View.VISIBLE);
                img.setVisibility(View.
                        VISIBLE);
                }

            @Override
            public void onFailure(Call<Mymovie> call, Throwable t) {

            }



    });


        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db openHelper = db.getInstance(getApplicationContext());
                SQLiteDatabase db1 = openHelper.getWritableDatabase();

                ContentValues contentValues = new ContentValues();
                contentValues.put(Contract.COLUMN_ID,mov_id);
                contentValues.put(Contract.MOVIE_TITLE,titlePublic);
                contentValues.put(Contract.MOVIE_URL,movieURL);
                long id = db1.insert(Contract.FAV_TABLE,null,contentValues);


            }
        });
        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerOnInitialised = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                final YouTubePlayer myouTubePlayer =youTubePlayer;
                Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                MovieInterface service2 = retrofit2.create(MovieInterface.class);
                Call<Video> call2 = service2.getVideo(mov_id+"","09d81dbea54787a5742e5f0adfc8ae0e");
                call2.enqueue(new Callback<Video>() {
                    @Override
                    public void onResponse(Call<Video> call, Response<Video> response) {
                        Video mv =response.body();

                        ArrayList<VideoResults> vr = (ArrayList<VideoResults>) mv.getResults();
                        vdeoUrl = vr.get(0).getKey();
                        Log.i("vdeourl",vdeoUrl);
                        myouTubePlayer.loadVideo(vdeoUrl);
                    }

                    @Override
                    public void onFailure(Call<Video> call, Throwable t) {

                    }



                });

            //    youTubePlayer.loadVideo("");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        youTubePlayerView.initialize("AIzaSyAKuIajIt1wch5xfA6hS_HOjTlDkgGX76o",youTubePlayerOnInitialised);

    }

}
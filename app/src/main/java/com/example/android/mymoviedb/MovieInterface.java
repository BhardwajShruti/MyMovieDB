package com.example.android.mymoviedb;

import android.graphics.Movie;
import android.media.Image;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shruti on 17-10-2017.
 */

public interface MovieInterface {


    @GET("movie/popular")
    Call<MovieList> getMovie(@Query("api_key") String api_key);

   // https://api.themoviedb.org/3/movie/now_playing?api_key=09d81dbea54787a5742e5f0adfc8ae0e

    @GET("movie/now_playing")
    Call<MovieList> getMovieNow_playing(@Query("api_key") String api_key);
    @GET("movie/top_rated")
    Call<MovieList> getMovieTop_rated(@Query("api_key") String api_key);

    @GET("tv/popular")
    Call<tvList> gettv(@Query("api_key") String api_key);

    @GET("tv/top_rated")
    Call<tvList> gettvtop(@Query("api_key") String api_key);
    @GET("tv/on_the_air")
    Call<tvList> gettvnowAir(@Query("api_key") String api_key);

//https://api.themoviedb.org/3/search/multi?api_key=09d81dbea54787a5742e5f0adfc8ae0e&language=en-US&query=happy&page=1&include_adult=false
@GET("search/multi?")
Call<Search> getSearch(@Query("api_key") String api_key,@Query("query")String query);

    //https://api.themoviedb.org/3/movie/440021/videos?api_key=09d81dbea54787a5742e5f0adfc8ae0e
    @GET("movie/{id}/videos")
    Call<Video> getVideo(@Path("id")String id,@Query("api_key") String api_key);

   // https://api.themoviedb.org/3/tv/popular?api_key=09d81dbea54787a5742e5f0adfc8ae0e&language=en-US&page=1
    @GET("{userpic}")
    Call<URL> getPic(@Path("userpic") String userpic);
    @GET("{movie_id}")
    Call<Mymovie> getMovieIntent(@Path("movie_id")String id ,  @Query("api_key") String api_key);
    //https://api.themoviedb.org/3/movie/346364?api_key=09d81dbea54787a5742e5f0adfc8ae0e
   // https://api.themoviedb.org/3/movie/popular?api_key=09d81dbea54787a5742e5f0adfc8ae0e&language=en-US&page=1
  //  movie/popular?api_key=09d81dbea54787a5742e5f0adfc8ae0e&language=en-US&page=1
}

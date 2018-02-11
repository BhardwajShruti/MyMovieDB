package com.example.android.mymoviedb;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shruti on 02-11-2017.
 */

public class MovieFrame extends Fragment {
    ListView listView;
    RecyclerView gridView,gridView1,gridView3;
    RecyclerView before,current,after;

    static ArrayList<String> mlist=new ArrayList<>();
    ArrayList<Result> popularList = new ArrayList<>();
    static ArrayList<String> posterUrl = new ArrayList<>();
    ArrayList<Integer> movie_id = new ArrayList<>();

    static ArrayList<String> nplist=new ArrayList<>();
    ArrayList<Result> nowPlayingList = new ArrayList<>();
    static ArrayList<String> nowPlayingposterUrl = new ArrayList<>();
    ArrayList<Integer> nowPlayingmovie_id = new ArrayList<>();

    static ArrayList<String> trlist=new ArrayList<>();
    ArrayList<Result> topRatedList = new ArrayList<>();
    static ArrayList<String> ntopRatedposterUrl = new ArrayList<>();
    ArrayList<Integer> topRatedmovie_id = new ArrayList<>();
    public MovieFrame() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieInterface service = retrofit.create(MovieInterface.class);
        Call<MovieList> call = service.getMovie("09d81dbea54787a5742e5f0adfc8ae0e");
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                MovieList tvlist=response.body();

                popularList =(ArrayList<Result>) tvlist.getResults();

                TvFrame.mlist = new ArrayList<String>();
                for(int i =0;i<popularList.size();i++) {
                    Result r = popularList.get(i);
                    String t = r.getTitle();
                    String imageURL = r.getPosterPath();
                    posterUrl.add(imageURL);
                    movie_id.add(r.getId());
                    mlist.add(t);
                }


            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Toast.makeText(MainActivity.this, "call failed", Toast.LENGTH_SHORT).show();
            }
        });
        Call<MovieList> call1 = service.getMovieNow_playing("09d81dbea54787a5742e5f0adfc8ae0e");
        call1.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                MovieList tvlist=response.body();

                nowPlayingList =(ArrayList<Result>) tvlist.getResults();

            //    TvFrame.mlist = new ArrayList<String>();
                for(int i =0;i<nowPlayingList.size();i++) {
                    Result r = nowPlayingList.get(i);
                    String t = r.getTitle();
                    String imageURL = r.getPosterPath();
                    nowPlayingposterUrl.add(imageURL);
                    nowPlayingmovie_id.add(r.getId());
                    nplist.add(t);
                }


            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Toast.makeText(MainActivity.this, "call failed", Toast.LENGTH_SHORT).show();
            }
        });
        Call<MovieList> call3 = service.getMovieTop_rated("09d81dbea54787a5742e5f0adfc8ae0e");
        call3.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                MovieList tvlist=response.body();

                topRatedList =(ArrayList<Result>) tvlist.getResults();

                //    TvFrame.mlist = new ArrayList<String>();
                for(int i =0;i<topRatedList.size();i++) {
                    Result r = topRatedList.get(i);
                    String t = r.getTitle();
                    String imageURL = r.getPosterPath();
                    ntopRatedposterUrl.add(imageURL);
                    topRatedmovie_id.add(r.getId());
                    trlist.add(t);
                }


            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Toast.makeText(MainActivity.this, "call failed", Toast.LENGTH_SHORT).show();
            }
        });
        View view = inflater.inflate(R.layout.fragment_movie_frame, container, false);

        //  ListView listView = (ListView) view.findViewById(R.id.movieList);
        gridView = (RecyclerView) view.findViewById(R.id.popularMovieList) ;
        gridView1 = (RecyclerView) view.findViewById(R.id.nowPlayingList) ;
gridView3 = (RecyclerView) view.findViewById(R.id.TopRatedMovieList) ;
        //  gridView.setAdapter(new );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        gridView.setLayoutManager(layoutManager);

        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        gridView1.setLayoutManager(layoutManager1);

        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        gridView3.setLayoutManager(layoutManager3);

        MyAdapter adapter = new MyAdapter(this.getActivity(),mlist,posterUrl,movie_id);
        gridView.setAdapter(adapter);

        MyAdapter adapter1 = new MyAdapter(this.getActivity(),nplist,nowPlayingposterUrl,nowPlayingmovie_id);
        gridView1.setAdapter(adapter1);

        MyAdapter adapter3 = new MyAdapter(this.getActivity(),trlist,ntopRatedposterUrl,topRatedmovie_id);
        gridView3.setAdapter(adapter3);

        before = (RecyclerView) view.findViewById(R.id.before);
        after = (RecyclerView) view.findViewById(R.id.after);
        current = (RecyclerView) view.findViewById(R.id.current);

  RecyclerView.LayoutManager layoutManagerB = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        before.setLayoutManager(layoutManagerB);
        RecyclerView.LayoutManager layoutManagerA = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        after.setLayoutManager(layoutManagerA);
        RecyclerView.LayoutManager layoutManagerC = new LinearLayoutManager(this.getActivity(),LinearLayoutManager.HORIZONTAL,false);
        current.setLayoutManager(layoutManagerC);

        HeaderAdapter adapterB = new HeaderAdapter(this.getActivity(),mlist,posterUrl,movie_id);
        before.setAdapter(adapterB);
       // before.scr);

//        ArrayList<String> mlistAfter = mlist;
//        String temp = mlistAfter.remove(0);
//        mlistAfter.add(temp);
//
        HeaderAdapter adapterA = new HeaderAdapter(this.getActivity(),mlist,posterUrl,movie_id);
        after.setAdapter(adapterA);

//        ArrayList<String> mlistCurrent = mlist;
//        String temp2 = mlistCurrent.remove(0);
//        mlistCurrent.add(temp2);
//

        HeaderAdapter adapterC = new HeaderAdapter(this.getActivity(),mlist,posterUrl,movie_id);
        current.setAdapter(adapterC);


        return  view;
    }

}


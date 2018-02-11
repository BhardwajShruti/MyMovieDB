package com.example.android.mymoviedb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    RecyclerView searchList;
    String query;
    ArrayList<Result> searchListMv;
    ArrayList<String> searchtitle = new ArrayList<>();
    ArrayList<String> searchURL = new ArrayList<>();
    ArrayList<Integer> searchID = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle extra = getIntent().getExtras();
        query = extra.getString("query");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieInterface service = retrofit.create(MovieInterface.class);
        Call<Search> call = service.getSearch("09d81dbea54787a5742e5f0adfc8ae0e", query);
        Log.i("called", query);
        call.enqueue(new Callback<Search>() {
            //    Log.i("called","calld");
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                Log.i("called", "calld");
                Search search = response.body();
                Log.i("search2", search.toString());
                searchListMv = (ArrayList<Result>) search.getResults();

                Log.i("size", searchListMv.size() + "");
                for (int i = 0; i < searchListMv.size(); i++) {
                    Result r = searchListMv.get(i);
                    String t = r.getTitle();
                    String imageURL = r.getPosterPath();
                    searchURL.add(imageURL);
                    searchID.add(r.getId());
                    searchtitle.add(t);
                    Log.i("ArraySize", searchID.size() + "");
                }

                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                //Toast.makeText(this, "call failed", Toast.LENGTH_SHORT).show();
                Log.i("called", "no");
            }
        });
        searchList = (RecyclerView) findViewById(R.id.searchList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        searchList.setLayoutManager(layoutManager);


        adapter = new SearchAdapter(this, searchtitle, searchURL, searchID);
        searchList.setAdapter(adapter);

//


    }
}

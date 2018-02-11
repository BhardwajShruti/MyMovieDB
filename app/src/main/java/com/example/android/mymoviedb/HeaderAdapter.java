package com.example.android.mymoviedb;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.android.mymoviedb.R.id.imageView;

/**
 * Created by shruti on 31-10-2017.
 */

public class HeaderAdapter extends Adapter<HeaderAdapter.ViewHolder> {   // Provide a reference to the views for each data item
    private List<String> values;
    private List<String> valuesUrl;
    private  ArrayList<Integer> movie_id;
    Context context;
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;

        ImageView imageView;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
         //   txtHeader = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    public void add(int position, String item1,String item2) {
        values.add(position, item1);
        valuesUrl.add(position,item2);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        valuesUrl.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HeaderAdapter(Context context, List<String> myDataset, List<String> myURLDataset, ArrayList<Integer> movie_id) {
        values = myDataset;
        this.context = context;
        valuesUrl = myURLDataset;
        this.movie_id = movie_id;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HeaderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.movie_header, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position);
        URL url;
        String urlString = "https://image.tmdb.org/t/p/w500/"+valuesUrl.get(position);

        try {
            url =new URL(urlString);
            Picasso.with(context)
                    .load(urlString)
                    .placeholder(R.drawable.ic_home_black_24dp) // optional
                    .error(R.drawable.ic_notifications_black_24dp)         // optional
                    .into(holder.imageView);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        // URL mvUrl = valuesUrl.get(position);
       // holder.txtHeader.setText(name);

//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context,MovieActivity.class);
//                context.startActivity(i);
//            }
//        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,MovieActivity.class);
                int position = holder.getAdapterPosition();
                i.putExtra("id",movie_id.get(position));



                context.startActivity(i);

            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}

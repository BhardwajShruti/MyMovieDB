package com.example.android.mymoviedb;

import java.io.Serializable;

/**
 * Created by shruti on 05-11-2017.
 */



import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Video implements Serializable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("results")
        @Expose
        private List<VideoResults> results = null;
        private final static long serialVersionUID = 7882497598290827593L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<VideoResults> getResults() {
            return results;
        }

        public void setResults(List<VideoResults> results) {
            this.results = results;
        }

    }

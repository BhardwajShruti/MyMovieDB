package com.example.android.mymoviedb;

/**
 * Created by shruti on 31-10-2017.
 */





import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class MovieList implements Serializable
    {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;
        @SerializedName("results")
        @Expose
        private ArrayList<Result> results = null;
        private final static long serialVersionUID = 8920571499661583126L;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }

        public ArrayList<Result> getResults() {
            return results;
        }

        public void setResults(ArrayList<Result> results) {
            this.results = results;
        }

    }



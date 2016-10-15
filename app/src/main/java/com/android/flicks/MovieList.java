package com.android.flicks;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;


public class MovieList extends Activity {
    ArrayList<movie> movies;

    @Override
    public ActionBar getActionBar() {
        return super.getActionBar();
    }

//    ArrayAdapter<movie> moviesAdapter;
    MovieArrayAdapter moviesAdapter;
    ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        movies = new ArrayList<movie>();
        movieList = (ListView)findViewById(R.id.movieListView);
        moviesAdapter = new MovieArrayAdapter(this, movies);
        movieList.setAdapter(moviesAdapter);
        getMovieList();
    }
    public MovieList() {
        super();
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    public void getMovieList() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", new JsonHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray resultsArray = response.getJSONArray("results");
                    movies.addAll(movie.getData(resultsArray));
                    moviesAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

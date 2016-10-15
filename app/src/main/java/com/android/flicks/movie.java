package com.android.flicks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vsuryawanshi on 10/12/16.
 */
public class movie {
    String movieDescription, movieTitle, imagePath, backdropPath;

    public String getMovieDescription() {
        return movieDescription;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getImagePath() {
        return "https://image.tmdb.org/t/p/w342/" + imagePath;
    }

    public String getBackdropPath() {
        return "https://image.tmdb.org/t/p/w342/" + backdropPath;
    }

    public movie(JSONObject movie) throws JSONException{
        this.movieTitle = movie.getString("title");
        this.movieDescription = movie.getString("overview");
        this.imagePath = movie.getString("poster_path");
        this.backdropPath = movie.getString("backdrop_path");
    }

    public static ArrayList<movie> getData(JSONArray movieArray) throws JSONException{
        ArrayList<movie> movies = new ArrayList<movie>();
        for(int i = 0; i<movieArray.length(); i++) {
           movies.add(new movie(movieArray.getJSONObject(i)));
        }
        return movies;
    }
}

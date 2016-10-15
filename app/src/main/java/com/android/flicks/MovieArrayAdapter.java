package com.android.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vsuryawanshi on 10/13/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<movie> {
    int orientation;
    Configuration config;
    MovieArrayAdapter(Context context, ArrayList<movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
        orientation = context.getResources().getConfiguration().orientation;
        config = context.getResources().getConfiguration();
    }
    private static class ViewHolder {
        TextView movieTitle;
        TextView movieDescription;
        ImageView movieImage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        movie movie=getItem(position);
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.image_item, parent, false);
            viewHolder.movieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
            viewHolder.movieDescription = (TextView) convertView.findViewById(R.id.movieDescription);
            viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.movieImage.setImageResource(0);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView);
//        imgView.setImageResource(0);
//        TextView movieTitle = (TextView) convertView.findViewById(R.id.movieTitle);
//        TextView movieDescription = (TextView) convertView.findViewById(R.id.movieDescription);
//        movieTitle.setText(movie.getMovieTitle());
//        movieDescription.setText(movie.getMovieDescription());
        viewHolder.movieTitle.setText(movie.getMovieTitle());
        viewHolder.movieDescription.setText(movie.getMovieDescription());
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.movieImage);
            // ...
        } else {
            Picasso.with(getContext()).load(movie.getImagePath()).into(viewHolder.movieImage);
        }
        return convertView;
    }
}

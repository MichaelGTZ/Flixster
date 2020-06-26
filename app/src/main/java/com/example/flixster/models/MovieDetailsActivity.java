package com.example.flixster.models;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flixster.R;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {
    // The movie to display
    Movie movie;

    // View Objects
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    TextView tvPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Resolve view objects
        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        tvPopularity = findViewById(R.id.tvPopularity);


        // Unwrap the movie passed in via intent
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // Set title and overview text
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        //Convert vote average from 0-10 to 0-5
        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        tvPopularity.setText(String.format("Popularity: %.2f  (based on %d votes)", movie.getPopularity(), movie.getViewCount()));
    }
}

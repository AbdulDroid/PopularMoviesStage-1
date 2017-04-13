package com.kafilicious.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kafilicious.popularmovies.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

/*
 * Copyright (C) 2017 Popular Movies, Stage 1
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

public class DetailActivity extends AppCompatActivity {

    String MOVIE_TITLE = "title";
    String MOVIE_OVERVIEW = "overview";
    String MOVIE_RELEASE = "release_date";
    String MOVIE_POSTER = "poster_path";
    String MOVIE_VOTE_AVERAGE = "vote_average";
    String MOVIE_VOTE_COUNT = "vote_count";
    String MOVIE_BACK_DROP = "backdrop_path";

    private TextView titleTextView, releaseDateTextView,ratingTextView,voteCountTextView,overviewTextView,voteAverageTextView;
    private ImageView posterImageView, backDropImageView;
    private RatingBar ratingBar;
    private CollapsingToolbarLayout collapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = this.getIntent();

        titleTextView = (TextView) findViewById(R.id.title_details);
        releaseDateTextView = (TextView) findViewById(R.id.year_details);
        ratingTextView = (TextView) findViewById(R.id.rating_score_detail);
        voteAverageTextView = (TextView) findViewById(R.id.average_vote);
        overviewTextView = (TextView) findViewById(R.id.plot_synopsis);
        voteCountTextView = (TextView) findViewById(R.id.num_of_votes_detail);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar_detail);
        posterImageView = (ImageView) findViewById(R.id.iv_movie_poster_details);
        backDropImageView = (ImageView) findViewById(R.id.iv_backdrop);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);

        if (intent != null && intent.hasExtra(MOVIE_TITLE)){
            actionBar.setTitle(intent.getStringExtra(MOVIE_TITLE) + " (" +
                    intent.getStringExtra(MOVIE_RELEASE).substring(0,4) + ")");
            collapsingToolbar.setTitle(intent.getStringExtra(MOVIE_TITLE) + " (" +
                    intent.getStringExtra(MOVIE_RELEASE).substring(0,4) + ")");
            collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.darker_gray));
            collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
            collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
            collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorPrimaryDark));

            titleTextView.setText(intent.getStringExtra(MOVIE_TITLE));
            releaseDateTextView.setText(intent.getStringExtra(MOVIE_RELEASE));
            voteCountTextView.setText("(" + String.valueOf(intent.getStringExtra(MOVIE_VOTE_COUNT)) + ")");
            overviewTextView.setText(intent.getStringExtra(MOVIE_OVERVIEW));
            voteAverageTextView.setText(intent.getStringExtra(MOVIE_VOTE_AVERAGE) + "/10");
            String url1 = NetworkUtils.buildMovieUrl(intent.getStringExtra(MOVIE_POSTER)).toString();
            Picasso.with(this)
                    .load(url1)
                    .into(posterImageView);

            String url2 = NetworkUtils.buildMovieUrl(intent.getStringExtra(MOVIE_BACK_DROP)).toString();
            Picasso.with(this)
                    .load(url2)
                    .into(backDropImageView);

            double voteAverage = Double.parseDouble(intent.getStringExtra(MOVIE_VOTE_AVERAGE));
            voteAverage = (voteAverage/10)*5;
            String rating = String.format("%.1f",voteAverage);
            voteAverage = Double.parseDouble(rating);

            ratingTextView.setText(rating);
            ratingBar.setRating((float)voteAverage);
            ratingBar.setStepSize((float)0.1);



        }





    }
}

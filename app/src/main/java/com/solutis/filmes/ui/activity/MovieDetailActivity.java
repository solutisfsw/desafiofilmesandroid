package com.solutis.filmes.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.solutis.filmes.R;
import com.solutis.filmes.model.Movie;
import com.solutis.filmes.repository.MovieRepository;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w200";
    private static MovieRepository movieRepository;

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieYear;
    private TextView movieDescription;
    private Button movieFavButton;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieRepository = new MovieRepository(this);

        setupFields();
        getMovieInExtra();
        setupFavButton();
    }

    private void getMovieInExtra() {
        Intent intent = getIntent();
        if (intent.hasExtra("movie")) {
            this.movie = (Movie) intent.getSerializableExtra("movie");
            fillFields();
        }
    }

    private void setupFields() {
        moviePoster = findViewById(R.id.detail_movie_poster);
        movieTitle = findViewById(R.id.detail_movie_title);
        movieYear = findViewById(R.id.detail_movie_year);
        movieFavButton = findViewById(R.id.detail_fav_button);
        movieDescription = findViewById(R.id.detail_movie_description);
    }

    private void setupFavButton() {
        movieFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieRepository.saveFavoriteMovie(movie, new MovieRepository.MovieCallback() {
                    @Override
                    public void success(Object result) {
                        Resources resources = getResources();

                        Drawable drawable = resources.getDrawable(R.drawable.ic_favorite_black);
                        movieFavButton.setBackground(drawable);
                        Toast.makeText(MovieDetailActivity.this, "Filme salvo com sucesso", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void fail(String error) {
                        Toast.makeText(MovieDetailActivity.this, "Erro ao salvar filme", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void fillFields() {
        this.movieTitle.setText(this.movie.getTitle());
        this.movieYear.setText(this.movie.getReleaseDate().split("-")[0]);
        this.movieDescription.setText(this.movie.getOverview());

        Glide.with(this).load(BASE_URL_IMG + movie.getPosterPath()).centerCrop().into(moviePoster);
    }
}

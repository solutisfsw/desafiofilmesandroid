package com.solutis.filmes.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.solutis.filmes.R;
import com.solutis.filmes.model.Movie;
import com.solutis.filmes.ui.activity.MovieDetailActivity;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> {

    protected Context context;
    private List<Movie> movies;

    public MovieListAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = this.movies.get(position);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            context.getApplicationContext().startActivity(intent);
        });

        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }


    public void add(Movie movie) {
        movies.add(movie);
        notifyItemInserted(movies.size() - 1);
    }

    public void addAll(List<Movie> newMovies) {
        for (Movie movie : newMovies) {
            add(movie);
        }
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w200";

        private TextView movieTitle;
        private TextView movieDescription;
        private ImageView moviePoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.item_movie_title);
            movieDescription = itemView.findViewById(R.id.item_movie_description);
            moviePoster = itemView.findViewById(R.id.item_movie_poster);


        }

        public void bind(Movie movie) {
            movieTitle.setText(movie.getTitle());
            movieDescription.setText(movie.getOverview());

            Glide
                    .with(context)
                    .load(BASE_URL_IMG + movie.getPosterPath())
                    .centerCrop()
                    .into(moviePoster);

        }
    }
}

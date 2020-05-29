package com.solutis.filmes.repository;

import android.content.Context;

import com.solutis.filmes.asynctask.BaseAsyncTask;
import com.solutis.filmes.database.MovieDatabase;
import com.solutis.filmes.database.dao.MovieDao;
import com.solutis.filmes.model.Movie;
import com.solutis.filmes.model.PagedMovie;
import com.solutis.filmes.retrofit.TheMovieDbRetrofit;
import com.solutis.filmes.retrofit.callback.BaseCallback;
import com.solutis.filmes.retrofit.service.MovieService;

public class MovieRepository {

    private final MovieDao dao;
    private TheMovieDbRetrofit theMovieDbRetrofit;
    private MovieService movieService;

    public MovieRepository(Context context) {
        MovieDatabase db = MovieDatabase.getInstance(context);
        this.dao = db.getProdutoDAO();

        this.theMovieDbRetrofit = new TheMovieDbRetrofit();
        this.movieService = this.theMovieDbRetrofit.getMovieService();
    }

    public void getUpcomingMovies(int page, final MovieCallback<PagedMovie> callback) {
        movieService.getUpcomingMovies(page).enqueue(new BaseCallback<>(new BaseCallback.CallBackResponse<PagedMovie>() {
            @Override
            public void success(PagedMovie result) {
                callback.success(result);
            }

            @Override
            public void fail(String error) {
                callback.fail(error);
            }
        }));
    }

    public void getTopRatedMovies(int page, final MovieCallback<PagedMovie> callback) {
        movieService.getTopRatedMovies(page).enqueue(new BaseCallback<>(new BaseCallback.CallBackResponse<PagedMovie>() {
            @Override
            public void success(PagedMovie result) {
                callback.success(result);
            }

            @Override
            public void fail(String error) {
                callback.fail(error);
            }
        }));
    }

    public void getPopularMovies(int page, final MovieCallback<PagedMovie> callback) {
        movieService.getPopularMovies(page).enqueue(new BaseCallback<>(new BaseCallback.CallBackResponse<PagedMovie>() {
            @Override
            public void success(PagedMovie result) {
                callback.success(result);
            }

            @Override
            public void fail(String error) {
                callback.fail(error);
            }
        }));
    }

    public void getFavoriteMovie(MovieCallback movieCallback) {
        new BaseAsyncTask<>(this.dao::getOnlyOneMovie, (BaseAsyncTask.EndsListener<Movie>) movieCallback::success).execute();
    }

    public void saveFavoriteMovie(Movie movie, MovieCallback movieCallback) {
        new BaseAsyncTask<>(() -> {
            Movie oldFavMovie = this.dao.getOnlyOneMovie();
            if(oldFavMovie != null) {
                this.dao.remove(oldFavMovie);
            }
            return this.dao.save(movie);
        }, (BaseAsyncTask.EndsListener<Long>) movieCallback::success).execute();
    }

    public interface MovieCallback<T> {
        void success(T result);

        void fail(String error);
    }
}

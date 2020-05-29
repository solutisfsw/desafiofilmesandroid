package com.solutis.filmes.retrofit.service;

import com.solutis.filmes.model.PagedMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/upcoming")
    Call<PagedMovie> getUpcomingMovies(
            @Query("page") int pageIndex
    );

    @GET("movie/popular")
    Call<PagedMovie> getPopularMovies(
            @Query("page") int pageIndex
    );

    @GET("movie/top_rated")
    Call<PagedMovie> getTopRatedMovies(
            @Query("page") int pageIndex
    );
}

package com.example.desafioandroid.retrofit.service;

import com.example.desafioandroid.model.Filme;
import com.example.desafioandroid.model.PageResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmeService {

    String API_KEY = "b71a12464038cacafc2752b079cba3ff";

    @GET("movie/popular?api_key="+ API_KEY+ "&language=pt-BR")
    Call<PageResult<Filme>> getPopular();

    @GET("movie/top_rated?api_key="+ API_KEY+ "&language=pt-BR")
    Call<PageResult<Filme>> getTopRated();

    @GET("movie/upcoming?api_key="+ API_KEY+ "&language=pt-BR")
    Call<PageResult<Filme>> getUpcoming();
}

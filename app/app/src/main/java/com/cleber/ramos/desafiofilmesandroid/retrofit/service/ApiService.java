package com.cleber.ramos.desafiofilmesandroid.retrofit.service;

import com.cleber.ramos.desafiofilmesandroid.model.Acervo;
import com.cleber.ramos.desafiofilmesandroid.model.FilmeDetalhe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String apiKey = "api_key=f863045c854e7dcfa984c9cef24e1afe&language=pt-BR";

    @GET("upcoming?" + apiKey)
    Call<Acervo> buscaFilmesMaisRecentes();

    @GET("upcoming?" + apiKey)
    Call<Acervo> buscaFilmesMaisRecentes(@Query("page") Integer numPagina);

    @GET("popular?" + apiKey)
    Call<Acervo> buscaFilmesMaisPopulares();

    @GET("popular?" + apiKey)
    Call<Acervo> buscaFilmesMaisPopulares(@Query("page") Integer numPagina);

    @GET("top_rated?" + apiKey)
    Call<Acervo> buscaFilmesMaisAvaliados();

    @GET("top_rated?" + apiKey)
    Call<Acervo> buscaFilmesMaisAvaliados(@Query("page") Integer numPagina);

    @GET("{idFilme}?" + apiKey)
    Call<FilmeDetalhe> buscaDetalhesDeUmFilme(@Path("idFilme") Integer idFilme);

}

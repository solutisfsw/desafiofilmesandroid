package br.com.solutis.viciadosemfilmes.service;

import br.com.solutis.viciadosemfilmes.model.Filme;
import br.com.solutis.viciadosemfilmes.model.FilmesList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmeService {

    @GET("3/movie/upcoming")
    Call<FilmesList> buscarFilmesRecentes(@Query("api_key") String api_key);

    @GET("3/movie/popular")
    Call<FilmesList> buscarFilmesPopulares(@Query("api_key") String api_key);

    @GET("3/movie/top_rated")
    Call<FilmesList> buscarFilmesTopRated(@Query("api_key") String api_key);

    @GET("3/movie/{id}")
    Call<Filme> buscarFilme(@Path("id") long id, @Query("api_key") String api_key);
}

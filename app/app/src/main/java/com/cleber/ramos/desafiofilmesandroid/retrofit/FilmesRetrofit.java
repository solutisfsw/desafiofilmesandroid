package com.cleber.ramos.desafiofilmesandroid.retrofit;

import com.cleber.ramos.desafiofilmesandroid.retrofit.service.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmesRetrofit {

    private static final String URL_BASE = "https://api.themoviedb.org/3/movie/";
    public static final String URL_BASE_IMAGEM = "https://image.tmdb.org/t/p/w500/";
    private final ApiService apiService;

    public FilmesRetrofit() {
        OkHttpClient client = configuraClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private OkHttpClient configuraClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public ApiService getApiService() {
        return apiService;
    }

}

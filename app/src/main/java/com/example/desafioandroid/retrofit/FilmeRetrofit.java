package com.example.desafioandroid.retrofit;

import com.example.desafioandroid.retrofit.service.FilmeService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmeRetrofit {
    private static final String URL_BASE = "https://api.themoviedb.org/3/";
    private final FilmeService filmeService;

    public FilmeRetrofit() {
        OkHttpClient client = configuraClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        filmeService = retrofit.create(FilmeService.class);
    }

    private OkHttpClient configuraClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    public FilmeService getFilmeService() {
        return filmeService;
    }
}

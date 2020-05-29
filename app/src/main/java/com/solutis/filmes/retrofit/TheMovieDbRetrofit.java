package com.solutis.filmes.retrofit;

import com.solutis.filmes.retrofit.service.MovieService;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TheMovieDbRetrofit {

    private static final String API_URL = "https://api.themoviedb.org/3/";
    private final MovieService movieService;

    public TheMovieDbRetrofit() {
        OkHttpClient client = getOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptorLogging = getHttpLoggingInterceptor();
        Interceptor queryParametersInterceptor = getApiDefaultQueryParametersInterceptor();

        return new OkHttpClient.Builder().addInterceptor(interceptorLogging)
                .addInterceptor(queryParametersInterceptor).build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {

        HttpLoggingInterceptor interceptorLogging = new HttpLoggingInterceptor();
        interceptorLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptorLogging;
    }

    private static Interceptor getApiDefaultQueryParametersInterceptor() {
        return new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {

                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl
                        .newBuilder()
                        .addQueryParameter("api_key", "9071909213902bf84fe1a23d0aeb9b88")
                        .addQueryParameter("language", "en-US")
                        .build();

                Request.Builder requestBuilder = original.newBuilder().url(url);
                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        };
    }

    public MovieService getMovieService() {
        return movieService;
    }
}

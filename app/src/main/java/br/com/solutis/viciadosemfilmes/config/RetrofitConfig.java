package br.com.solutis.viciadosemfilmes.config;

import br.com.solutis.viciadosemfilmes.service.FilmeService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final FilmeService filmeService;

    public RetrofitConfig(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        filmeService = retrofit.create(FilmeService.class);
    }

    public FilmeService getFilmeService() {
        return filmeService;
    }
}

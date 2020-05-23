package com.example.desafioandroid.util;

import android.content.Context;

public class SharedPreferences {
    private android.content.SharedPreferences preferences;
    private android.content.SharedPreferences.Editor editor;
    private static final String NOME_ARQUIVO = "solutis.desafiofilmeandroid";
    private final String CHAVE_ID_MOVIE = "movie_id";

    public SharedPreferences(Context context) {
        this.preferences = context.getSharedPreferences(NOME_ARQUIVO, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void salvarIdMovie(int movie_id){
        editor.putInt(CHAVE_ID_MOVIE, movie_id);
        editor.commit();
    }

    public int getIdMovie(){
        return preferences.getInt(CHAVE_ID_MOVIE,0);
    }
}

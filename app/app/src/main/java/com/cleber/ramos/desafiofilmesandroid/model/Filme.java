package com.cleber.ramos.desafiofilmesandroid.model;

import com.google.gson.annotations.SerializedName;

public class Filme
{
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getId() {
        return id;
    }
}

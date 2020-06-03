package com.cleber.ramos.desafiofilmesandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmeDetalhe {

    @SerializedName("genres")
    public List<Genre> genres = null;

    @SerializedName("overview")
    public String overview;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("tagline")
    public String tagline;

    @SerializedName("title")
    public String title;

    public List<Genre> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }
}

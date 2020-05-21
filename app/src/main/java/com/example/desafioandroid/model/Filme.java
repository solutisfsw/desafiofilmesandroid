package com.example.desafioandroid.model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Filme {

    private Integer id;

    @SerializedName("poster_path")
    private String postPath;

    private Boolean adult;

    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("original_language")
    private String originalLanguage;

    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private Double popularity;

    @SerializedName("vote_count")
    private Integer voteCount;

    private Boolean video;

    @SerializedName("vote_average")
    private Double voteAverage;

}

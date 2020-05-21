package com.example.desafioandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PageResult<T> {
    private Integer page;
    private List<T> results;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;
}

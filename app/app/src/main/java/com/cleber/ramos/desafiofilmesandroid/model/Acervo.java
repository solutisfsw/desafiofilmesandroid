package com.cleber.ramos.desafiofilmesandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Acervo {

    @SerializedName("results")
    @Expose
    private List<Filme> filmes;

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("dates")
    @Expose
    private Dates dates;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<Filme> getFilmes() {
        return filmes;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}


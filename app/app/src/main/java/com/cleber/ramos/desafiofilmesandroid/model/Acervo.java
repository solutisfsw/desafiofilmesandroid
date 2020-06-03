package com.cleber.ramos.desafiofilmesandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Acervo {

    @SerializedName("results")
    private List<Filme> filmes;

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_pages")
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


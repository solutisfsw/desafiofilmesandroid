package br.com.solutis.viciadosemfilmes.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmesList {

    @SerializedName("results")
    private List<Filme> filmesList;

    public List<Filme> getFilmesList() {
        return filmesList;
    }
}

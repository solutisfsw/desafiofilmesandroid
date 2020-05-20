package br.com.solutis.viciadosemfilmes.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Filme {

    @SerializedName("id")
    private final long id;

    @SerializedName("title")
    private final String titulo;

    @SerializedName("overview")
    private final String sinopse;

    @SerializedName("poster_path")
    private final String capa;

    @SerializedName("release_date")
    @Nullable
    private final Date lancamento;

    @SerializedName("tagline")
    @Nullable
    private final String tagline;

    @SerializedName("genres")
    @Nullable
    private final List<Genero> generoList;

    private boolean faved = false;

    public Filme(Long id, String title, String overview, String poster_path, Date lancamento, String tagline, List<Genero> generoList, boolean faved) {
        this.id = id;
        this.titulo = title;
        this.sinopse = overview;
        this.capa = poster_path;
        this.lancamento = lancamento;
        this.tagline = tagline;
        this.generoList = generoList;
        this.faved = faved;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getCapa() {
        return capa;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public String getTagline() {
        return tagline;
    }

    public List<Genero> getGeneroList() {
        return generoList;
    }

    public boolean isFaved() {
        return faved;
    }

    public void setFaved(boolean faved) {
        this.faved = faved;
    }
}

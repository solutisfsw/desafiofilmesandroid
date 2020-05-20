package br.com.solutis.viciadosemfilmes.model;

import com.google.gson.annotations.SerializedName;

public class Genero {

    @SerializedName("id")
    private final long id;

    @SerializedName("name")
    private final String name;

    public Genero(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

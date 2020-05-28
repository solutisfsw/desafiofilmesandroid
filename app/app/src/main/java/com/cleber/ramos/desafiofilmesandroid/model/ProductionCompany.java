package com.cleber.ramos.desafiofilmesandroid.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompany {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("logo_path")
    @Expose
    public String logoPath;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public String originCountry;
}

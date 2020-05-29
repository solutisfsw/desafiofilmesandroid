package com.solutis.filmes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Movie implements Serializable {

        @PrimaryKey
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("poster_path")
        @Expose
        private String posterPath;

        @SerializedName("adult")
        @Expose
        private Boolean adult;

        @SerializedName("overview")
        @Expose
        private String overview;

        @SerializedName("release_date")
        @Expose
        private String releaseDate;

        @Ignore
        @SerializedName("genre_ids")
        @Expose
        private List<Integer> genreIds = new ArrayList<Integer>();

        @SerializedName("original_title")
        @Expose
        private String originalTitle;

        @SerializedName("original_language")
        @Expose
        private String originalLanguage;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;

        @SerializedName("popularity")
        @Expose
        private Double popularity;

        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;

        @SerializedName("video")
        @Expose
        private Boolean video;

        @SerializedName("vote_average")
        @Expose
        private Double voteAverage;

        public Movie(){
            super();
        }

        public Movie(Integer id, String overview, String title) {
                this.id = id;
                this.overview = overview;
                this.title = title;
        }

        public Integer getId() {
                return id;
        }

        public String getPosterPath() {
                return posterPath;
        }

        public Boolean getAdult() {
                return adult;
        }

        public String getOverview() {
                return overview;
        }

        public String getReleaseDate() {
                return releaseDate;
        }

        public List<Integer> getGenreIds() {
                return genreIds;
        }

        public String getOriginalTitle() {
                return originalTitle;
        }

        public String getOriginalLanguage() {
                return originalLanguage;
        }

        public String getTitle() {
                return title;
        }

        public String getBackdropPath() {
                return backdropPath;
        }

        public Double getPopularity() {
                return popularity;
        }

        public Integer getVoteCount() {
                return voteCount;
        }

        public Boolean getVideo() {
                return video;
        }

        public Double getVoteAverage() {
                return voteAverage;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void setPosterPath(String posterPath) {
                this.posterPath = posterPath;
        }

        public void setAdult(Boolean adult) {
                this.adult = adult;
        }

        public void setOverview(String overview) {
                this.overview = overview;
        }

        public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
        }

        public void setGenreIds(List<Integer> genreIds) {
                this.genreIds = genreIds;
        }

        public void setOriginalTitle(String originalTitle) {
                this.originalTitle = originalTitle;
        }

        public void setOriginalLanguage(String originalLanguage) {
                this.originalLanguage = originalLanguage;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public void setBackdropPath(String backdropPath) {
                this.backdropPath = backdropPath;
        }

        public void setPopularity(Double popularity) {
                this.popularity = popularity;
        }

        public void setVoteCount(Integer voteCount) {
                this.voteCount = voteCount;
        }

        public void setVideo(Boolean video) {
                this.video = video;
        }

        public void setVoteAverage(Double voteAverage) {
                this.voteAverage = voteAverage;
        }
}

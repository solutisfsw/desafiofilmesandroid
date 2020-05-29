package com.solutis.filmes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.solutis.filmes.model.Movie;

@Dao
public interface MovieDao {

    @Insert
    long save(Movie movie);

    @Query("SELECT * FROM Movie ")
    Movie getOnlyOneMovie();

    @Delete
    void remove(Movie movie);

}

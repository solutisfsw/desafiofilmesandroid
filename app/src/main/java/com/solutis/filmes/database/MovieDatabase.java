package com.solutis.filmes.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.solutis.filmes.database.dao.MovieDao;
import com.solutis.filmes.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getProdutoDAO();

    public static MovieDatabase getInstance(Context context) {
        return Room.databaseBuilder(
                context,
                MovieDatabase.class,
                "filmes.db")
                .build();
    }
}

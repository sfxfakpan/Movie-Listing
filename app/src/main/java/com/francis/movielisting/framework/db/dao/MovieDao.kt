package com.francis.movielisting.framework.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.francis.core.data.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): DataSource.Factory<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovieList(movies: List<Movie>)
}
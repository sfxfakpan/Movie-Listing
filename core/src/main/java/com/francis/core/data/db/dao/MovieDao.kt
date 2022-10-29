package com.francis.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.francis.core.data.db.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun get(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovieList(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()
}
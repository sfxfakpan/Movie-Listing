package com.francis.core.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.francis.core.data.db.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun get(id: Int): Flow<Movie?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovieList(movies: List<Movie>)

    @Update
    suspend fun update(movie: Movie)

    @Query("DELETE FROM movies")
    suspend fun clearMovies()

}
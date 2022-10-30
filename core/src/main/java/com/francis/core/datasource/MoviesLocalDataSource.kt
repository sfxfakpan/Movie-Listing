package com.francis.core.datasource

import androidx.paging.PagingSource
import com.francis.core.data.db.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {

    fun get(id: Int): Flow<Movie?>

    fun getAll(): PagingSource<Int, Movie>

    suspend fun insertAll(movies: List<Movie>)

    suspend fun update(movie: Movie)

    suspend fun nuke()

}
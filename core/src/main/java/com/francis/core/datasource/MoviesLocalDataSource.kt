package com.francis.core.datasource

import androidx.paging.PagingSource
import com.francis.core.data.db.Movie

interface MoviesLocalDataSource {

    suspend fun insertAll(movies: List<Movie>)

    suspend fun get(id: Int): Movie?

    fun getAll(): PagingSource<Int, Movie>

    suspend fun nuke()
}
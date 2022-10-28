package com.francis.core.datasource

import androidx.paging.PagingSource
import com.francis.core.data.Movie

interface MoviesLocalDataSource {

    fun insertAll(movies: List<Movie>)

    fun get(id: Int): Movie?

    fun getAll(): PagingSource<Int, Movie>

    fun nuke()
}
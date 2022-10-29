package com.francis.movielisting.framework.datasource

import androidx.paging.PagingSource
import com.francis.core.data.db.Movie
import com.francis.core.data.db.dao.MovieDao
import com.francis.core.datasource.MoviesLocalDataSource
import javax.inject.Inject

class MovieRoomDataSource @Inject constructor(
    private val dao: MovieDao
): MoviesLocalDataSource {

    override suspend fun insertAll(movies: List<Movie>) {
        dao.saveMovieList(movies)
    }

    override suspend fun get(id: Int): Movie? {
        return dao.get(id)
    }

    override fun getAll(): PagingSource<Int, Movie> {
        return dao.getMovies()
    }

    override suspend fun nuke() {
        dao.clearMovies()
    }
}
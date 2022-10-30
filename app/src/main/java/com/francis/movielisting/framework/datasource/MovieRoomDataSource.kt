package com.francis.movielisting.framework.datasource

import androidx.paging.PagingSource
import com.francis.core.data.db.Movie
import com.francis.core.data.db.dao.MovieDao
import com.francis.core.datasource.MoviesLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRoomDataSource @Inject constructor(
    private val dao: MovieDao
): MoviesLocalDataSource {

    override suspend fun insertAll(movies: List<Movie>) {
        dao.saveMovieList(movies)
    }

    override fun get(id: Int): Flow<Movie?> {
        return dao.get(id)
    }

    override fun getAll(): PagingSource<Int, Movie> {
        return dao.getMovies()
    }

    override suspend fun update(movie: Movie) {
        return dao.update(movie)
    }

    override suspend fun nuke() {
        dao.clearMovies()
    }
}
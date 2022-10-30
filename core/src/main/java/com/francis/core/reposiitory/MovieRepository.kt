package com.francis.core.reposiitory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.francis.core.data.db.Movie
import com.francis.core.datasource.MoviesLocalDataSource
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.core.datasource.RemoteMediatorDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val mediatorDataSource: RemoteMediatorDataSource
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getMoviesFlow(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 100, prefetchDistance = 3),
            pagingSourceFactory = { moviesLocalDataSource.getAll() },
            remoteMediator = mediatorDataSource
        ).flow
    }

    suspend fun getMovie(id: Int) =
        moviesLocalDataSource.get(id)

    suspend fun fetchMovieCasts(id: Int) =
        moviesRemoteDataSource.fetchMovieCasts(id)

    suspend fun fetchMovieVideos(id: Int) =
        moviesRemoteDataSource.fetchMovieVideos(id)

}
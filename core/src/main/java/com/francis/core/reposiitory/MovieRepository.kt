package com.francis.core.reposiitory

import com.francis.core.datasource.MoviesLocalDataSource
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.core.datasource.RemoteMediatorDataSource

class MovieRepository(
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val mediatorDataSource: RemoteMediatorDataSource
) {


}
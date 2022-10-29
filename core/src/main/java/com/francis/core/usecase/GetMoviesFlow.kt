package com.francis.core.usecase

import androidx.paging.PagingData
import com.francis.core.data.db.Movie
import com.francis.core.reposiitory.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesFlow @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repository.getMoviesFlow()
    }
}
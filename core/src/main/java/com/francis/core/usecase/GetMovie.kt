package com.francis.core.usecase

import com.francis.core.data.db.Movie
import com.francis.core.reposiitory.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int) = flow<Movie?> {
        emit(repository.getMovie(id))
    }
}
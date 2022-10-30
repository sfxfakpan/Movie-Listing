package com.francis.core.usecase

import com.francis.core.reposiitory.MovieRepository
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int) = repository.getMovie(id)
}
package com.francis.core.usecase

import com.francis.core.data.Cast
import com.francis.core.reposiitory.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieCast @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int) = flow<List<Cast>?> {
        try {
            val response = repository.fetchMovieCasts(id)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                emit(body.results)
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }
}
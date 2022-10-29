package com.francis.core.usecase

import com.francis.core.data.Video
import com.francis.core.reposiitory.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieVideos @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int) = flow<List<Video>?> {
        try {
            val response = repository.fetchMovieVideos(id)
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
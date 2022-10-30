package com.francis.core.usecase

import androidx.annotation.Keep
import com.francis.core.reposiitory.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@Keep
class FetchNUpdate @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(id: Int, scope: CoroutineScope) {
        if (scope.isActive) {
            scope.launch(Dispatchers.IO) {
                try {
                    val response = repository.fetchMovieDetails(id)
                    if (response.isSuccessful && response.body() != null) {
                        val movie = response.body()!!
                        repository.updateMovieDetails(movie)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
package com.francis.core.datasource


import com.francis.core.data.response.CreditsResponse
import com.francis.core.data.response.MoviesResponse
import com.francis.core.data.response.VideosResponse
import retrofit2.Response

interface MoviesRemoteDataSource {

    suspend fun fetchMovies(page: Int?): Response<MoviesResponse>

    suspend fun fetchMovieVideos(id: Int): Response<VideosResponse>

    suspend fun fetchMovieCasts(id: Int): Response<CreditsResponse>
}
package com.francis.movielisting.framework.datasource

import com.francis.core.data.db.Movie
import com.francis.core.data.response.CreditsResponse
import com.francis.core.data.response.MoviesResponse
import com.francis.core.data.response.VideosResponse
import com.francis.core.datasource.MoviesRemoteDataSource
import com.francis.movielisting.framework.service.ApiClient
import retrofit2.Response
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val apiClient: ApiClient
): MoviesRemoteDataSource{

    override suspend fun fetchMovies(page: Int?): Response<MoviesResponse> {
        return apiClient.service.fetchDiscoverList(page)
    }

    override suspend fun fetchMovieVideos(id: Int): Response<VideosResponse> {
        return apiClient.service.fetchVideos(id)
    }

    override suspend fun fetchMovieCasts(id: Int): Response<CreditsResponse> {
        return apiClient.service.fetchCredits(id)
    }

    override suspend fun fetchMovieDetails(id: Int): Response<Movie> {
        return apiClient.service.fetchDetails(id)
    }
}
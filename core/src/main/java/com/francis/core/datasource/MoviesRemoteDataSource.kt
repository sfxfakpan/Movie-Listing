package com.francis.core.datasource


import com.francis.core.data.Cast
import com.francis.core.data.Movie
import com.francis.core.data.Video
import com.francis.core.data.response.CreditsResponse
import com.francis.core.data.response.MoviesResponse
import com.francis.core.data.response.VideosResponse
import retrofit2.Response

interface MoviesRemoteDataSource {

    suspend fun fetchMovies(page: Int): List<Movie>

    suspend fun loadMovieDetails(id: Int): Movie?

    suspend fun fetchMovieVideos(id: Int): List<Video>

    suspend fun fetchMovieCasts(id: Int): List<Cast>
}
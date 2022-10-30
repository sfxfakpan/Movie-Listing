package com.francis.movielisting.framework.service

import com.francis.core.data.response.CreditsResponse
import com.francis.core.data.response.MoviesResponse
import com.francis.core.data.response.VideosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object Api {
    private const val API_VERSION: Int = 3
    private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    private const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    private const val BASE_PROFILE_URL = "https://image.tmdb.org/t/p/w185"
    private const val BASE_YT_IMG_URL = "https://img.youtube.com/vi/"
    private const val BASE_YT_WATCH_URL = "https://www.youtube.com/watch?v="
    const val MAX_RATING = 10f

    fun getPosterUrl(path: String) = BASE_POSTER_URL + path
    fun getBackdropUrl(path: String) = BASE_BACKDROP_URL + path
    fun getProfileUrl(path: String) = BASE_PROFILE_URL + path
    fun getYoutubeImageUrl(youtubeId: String) = "$BASE_YT_IMG_URL$youtubeId/hqdefault.jpg"
    fun getYoutubeWatchUrl(youtubeId: String) = "$BASE_YT_WATCH_URL$youtubeId"/**/
    fun getRuntimeDateFormat() = ("yyyy-MM-dd")

    interface MoviesService {

        @GET("/$API_VERSION/discover/movie")
        suspend fun fetchDiscoverList(@Query("page") page: Int?): Response<MoviesResponse>

        @GET("/$API_VERSION/movie/{id}/credits")
        suspend fun fetchCredits(@Path("id") id: Int): Response<CreditsResponse>

        @GET("/$API_VERSION/movie/{id}/videos")
        suspend fun fetchVideos(@Path("id") id: Int): Response<VideosResponse>
    }
}
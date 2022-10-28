package com.francis.movielisting.framework.service

import retrofit2.Retrofit
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val retrofit: Retrofit
) {
    val service: Api.MoviesService
        get() = retrofit.create(Api.MoviesService::class.java)
}
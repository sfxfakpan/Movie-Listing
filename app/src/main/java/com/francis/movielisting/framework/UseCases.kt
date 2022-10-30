package com.francis.movielisting.framework

import com.francis.core.usecase.*
import javax.inject.Inject

data class UseCases @Inject constructor(
    val updateMovieDetails: FetchNUpdate,
    val getMovieVideos: GetMovieVideos,
    val getMoviesFlow: GetMoviesFlow,
    val getMovieCast: GetMovieCast,
    val getMovie: GetMovie
)
package com.francis.movielisting.framework

import com.francis.core.usecase.GetMovie
import com.francis.core.usecase.GetMovieCast
import com.francis.core.usecase.GetMovieVideos
import com.francis.core.usecase.GetMoviesFlow
import javax.inject.Inject

data class UseCases @Inject constructor(
    val getMovieVideos: GetMovieVideos,
    val getMoviesFlow: GetMoviesFlow,
    val getMovieCast: GetMovieCast,
    val getMovie: GetMovie
)
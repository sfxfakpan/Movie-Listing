package com.francis.movielisting.presentation.binding

import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.francis.core.data.db.Movie
import com.francis.movielisting.framework.service.Api

@BindingAdapter("bind_rating_bar", "bind_rating_stars")
fun RatingBar.bindRatingBar(movie: Movie?, stars: Int) {
    movie?.let { this.rating = stars * ((it.voteAverage / Api.MAX_RATING)) }
}

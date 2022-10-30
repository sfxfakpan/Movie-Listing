package com.francis.movielisting.presentation.binding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.francis.movielisting.R
import com.francis.movielisting.framework.service.Api

@BindingAdapter("bind_backdrop_path_loading", "bind_progress")
fun ImageView.bindBackdropImageWithPicassoLoading(path: String?, progressBar: ProgressBar) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    progressBar.visibility = View.VISIBLE
    load(Api.getBackdropUrl(path)){
        crossfade(true)
        transformations(RoundedCornersTransformation(4f, 1f))
        error(R.drawable.ic_baseline_image_24)

    }
}

@BindingAdapter("bind_poster_path")
fun ImageView.bindPosterImageWithPicasso(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    load(Api.getPosterUrl(path)) {
        crossfade(true)
        error(R.drawable.ic_baseline_image_24)
        transformations(RoundedCornersTransformation(topLeft = 4f, topRight = 1f))
    }
}

@BindingAdapter("bind_profile_path")
fun ImageView.bindProfileImageWithCoil(path: String?) {
    if (path.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    load(Api.getProfileUrl(path)) {
        crossfade(true)
        error(R.drawable.ic_baseline_image_24)
    }
}

@BindingAdapter("bind_video_thumbnail")
fun ImageView.bindVideoThumbnailWithPicasso(youtubeId: String?) {
    if (youtubeId.isNullOrBlank()) {
        this.setImageResource(R.drawable.ic_baseline_image_24)
        return
    }
    load(Api.getYoutubeImageUrl(youtubeId)){
        crossfade(true)
        error(R.drawable.ic_baseline_image_24)
    }
}

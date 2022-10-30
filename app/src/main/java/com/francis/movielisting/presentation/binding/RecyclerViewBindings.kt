package com.francis.movielisting.presentation.binding

import androidx.databinding.BindingAdapter
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.francis.core.data.Cast
import com.francis.core.data.Video
import com.francis.core.data.db.Movie
import com.francis.movielisting.presentation.adapter.CastListAdapter
import com.francis.movielisting.presentation.adapter.LoaderStateAdapter
import com.francis.movielisting.presentation.adapter.MovieListAdapter
import com.francis.movielisting.presentation.adapter.VideoListAdapter
import com.francis.movielisting.presentation.util.GoToMovie
import com.francis.movielisting.presentation.util.GoToVideo
import kotlinx.coroutines.launch

@BindingAdapter("bind_cast_list")
fun RecyclerView.bindCastList(items: List<Cast>?) {
    if (items == null) return
    if (this.adapter == null) this.adapter = CastListAdapter()
    (this.adapter as CastListAdapter).submitList(items)
}

@BindingAdapter("bind_video_list", "bind_view_model_goto_video")
fun RecyclerView.bindVideoList(items: List<Video>?, goTo: GoToVideo) {
    if (items == null) return
    if (this.adapter == null) this.adapter = VideoListAdapter(goTo)
    (this.adapter as VideoListAdapter).submitList(items)
}

@BindingAdapter("bind_movie_list", "bind_view_model_goto_movie")
fun RecyclerView.bindMovieList(items: PagingData<Movie>?, goTo: GoToMovie) {
    if (this.adapter == null) {
        this.adapter = MovieListAdapter(goTo).apply {
            withLoadStateFooter(LoaderStateAdapter {
                retry()
            })
        }
    }
    findViewTreeLifecycleOwner()?.lifecycleScope?.launch {
        if (items != null) {
            (adapter as MovieListAdapter).submitData(items)
        }
    }
}

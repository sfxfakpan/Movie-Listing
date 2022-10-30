package com.francis.movielisting.presentation.util

import androidx.lifecycle.MutableLiveData
import com.francis.core.data.Video
import com.francis.core.data.db.Movie

interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<Movie>>

    fun goTo(movie: Movie) {
        goToMovieDetailsEvent.value = Event(movie)
    }
}
interface GoToVideo {
    val goToVideoEvent: MutableLiveData<Event<Video>>

    fun goTo(video: Video) {
        goToVideoEvent.value = Event(video)
    }
}
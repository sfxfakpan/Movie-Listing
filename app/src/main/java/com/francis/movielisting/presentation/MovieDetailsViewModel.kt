package com.francis.movielisting.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.francis.core.data.Cast
import com.francis.core.data.Video
import com.francis.core.data.db.Movie
import com.francis.movielisting.framework.UseCases
import com.francis.movielisting.presentation.util.Event
import com.francis.movielisting.presentation.util.GoToVideo
import com.francis.movielisting.presentation.util.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val useCases: UseCases
): ViewModel(), GoToVideo {

    lateinit var movie: LiveData<Movie?>
    lateinit var castList: LiveData<List<Cast>?>
    lateinit var videoList: LiveData<List<Video>?>

    override val goToVideoEvent: MutableLiveData<Event<Video>> = MutableLiveData()

    fun init(movieId: Int) {
        movie = liveDataBlockScope {
            useCases.getMovie(movieId).asLiveData()
        }

        videoList = liveDataBlockScope {
            useCases.getMovieVideos(movieId).asLiveData()
        }

        castList = liveDataBlockScope {
            useCases.getMovieCast(movieId).asLiveData()
        }
    }
}
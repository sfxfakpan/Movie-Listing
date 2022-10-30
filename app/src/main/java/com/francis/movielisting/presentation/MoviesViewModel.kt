package com.francis.movielisting.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.francis.core.data.db.Movie
import com.francis.movielisting.framework.UseCases
import com.francis.movielisting.presentation.util.Event
import com.francis.movielisting.presentation.util.GoToMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel(), GoToMovie {

    override val goToMovieDetailsEvent: MutableLiveData<Event<Movie>> = MutableLiveData()

    val movieList: LiveData<PagingData<Movie>> = useCases.getMoviesFlow()
        .cachedIn(viewModelScope)
        .asLiveData()

}
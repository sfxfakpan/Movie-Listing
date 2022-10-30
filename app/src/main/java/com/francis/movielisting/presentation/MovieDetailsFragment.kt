package com.francis.movielisting.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.francis.movielisting.databinding.FragmentMovieDetailsBinding
import com.francis.movielisting.framework.service.Api
import com.francis.movielisting.presentation.util.EventObserver
import com.francis.movielisting.presentation.util.openUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment: BaseFragment(true){

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false).apply {
            viewmodel = this@MovieDetailsFragment.viewModel
            lifecycleOwner = this@MovieDetailsFragment.viewLifecycleOwner
        }
        return binding.root
    }

    override fun onInitialized() {
        viewModel.init(args.id)
    }

    override fun setupViewModelObservers() {
        viewModel.goToVideoEvent.observe(
            viewLifecycleOwner,
            EventObserver { openUrl(Api.getYoutubeWatchUrl(it.key)) })
    }
}
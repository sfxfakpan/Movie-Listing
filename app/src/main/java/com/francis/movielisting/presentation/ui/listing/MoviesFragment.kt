package com.francis.movielisting.presentation.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.francis.movielisting.databinding.FragmentMoviesBinding
import com.francis.movielisting.presentation.BaseFragment
import com.francis.movielisting.presentation.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment(false) {

    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(layoutInflater).apply {
            viewmodel = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    private fun navigateToMovieDetails(movieId: Int, title: String) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
            movieId, title
        )
        findNavController().navigate(action)
    }

    override fun setupViewModelObservers() {
        viewModel.goToMovieDetailsEvent.observe(
            viewLifecycleOwner,
            EventObserver { navigateToMovieDetails(it.id, it.title) })
    }
}
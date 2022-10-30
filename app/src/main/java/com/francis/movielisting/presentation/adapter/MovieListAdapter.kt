package com.francis.movielisting.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.francis.core.data.db.Movie
import com.francis.movielisting.databinding.ListItemMovieGridBinding
import com.francis.movielisting.presentation.util.GoToMovie

class MovieListAdapter internal constructor(
    private val goToMovie: GoToMovie,
) : PagingDataAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffCallback()) {

    class ViewHolder(private val binding: ListItemMovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(GoToMovie: GoToMovie, item: Movie) {
            binding.goToInterface = GoToMovie
            binding.movie = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(goToMovie, it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemMovieGridBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

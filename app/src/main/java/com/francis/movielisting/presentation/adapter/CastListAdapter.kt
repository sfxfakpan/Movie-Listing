package com.francis.movielisting.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francis.core.data.Cast
import com.francis.movielisting.databinding.ListItemCastBinding

class CastListAdapter :
    ListAdapter<(Cast), CastListAdapter.ViewHolder>(CastDiffCallback()) {

    class ViewHolder(private val binding: ListItemCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cast) {
            binding.cast = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemCastBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    private class CastDiffCallback : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
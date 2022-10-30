package com.francis.movielisting.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.francis.movielisting.databinding.ItemLoaderBinding

class LoaderStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    inner class LoaderViewHolder(val binding: ItemLoaderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.binding.apply {
            pbLoader.isVisible = loadState is LoadState.Loading
            tvError.isVisible = loadState is LoadState.Error
            btnRetry.apply {
                isVisible = loadState is LoadState.Error
                setOnClickListener { retry.invoke() }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LoaderViewHolder(ItemLoaderBinding.inflate(inflater, parent, false))
    }
}
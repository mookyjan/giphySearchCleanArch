package com.mudassir.giphyapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.LoadingBarBinding

class GiphyLoadingStateViewHolder(private val binding: LoadingBarBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup): GiphyLoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_bar, parent, false)

            val binding = LoadingBarBinding.bind(view)

            return GiphyLoadingStateViewHolder(
                binding
            )
        }
    }
}
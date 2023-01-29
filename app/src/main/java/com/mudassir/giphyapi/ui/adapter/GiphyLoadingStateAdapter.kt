package com.mudassir.giphyapi.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class GiphyLoadingStateAdapter  : LoadStateAdapter<GiphyLoadingStateViewHolder>() {

    override fun onBindViewHolder(holder: GiphyLoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): GiphyLoadingStateViewHolder {
        return GiphyLoadingStateViewHolder.create(parent)
    }
}
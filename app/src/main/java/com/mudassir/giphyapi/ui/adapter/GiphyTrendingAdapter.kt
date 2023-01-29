package com.mudassir.giphyapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.SingleItemGiphyBinding

class GiphyTrendingAdapter :
    PagingDataAdapter<GiphyDomainModel, GiphyTrendingAdapter.ItemViewHolder>(DiffCallback()) {

    private var callbacks: Callbacks? = null
    fun setupListener(listener: Callbacks?) {
        this.callbacks = listener
    }

    interface Callbacks {
        fun onGiphyItemClick(view: View, item: GiphyDomainModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding =
            SingleItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(
            viewBinding
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let { giphyItem ->
            holder.bind(giphyItem)
        }
    }

    inner class ItemViewHolder(private val binding: SingleItemGiphyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageView: ImageView = binding.ivGiphy
        fun bind(item: GiphyDomainModel) = with(itemView) {
            Glide.with(this).asGif().load(item.url)
                .error(R.drawable.ic_error).into(imageView)
            imageView.transitionName = item.url
            binding.btnAddToFav.setOnClickListener {
                callbacks?.onGiphyItemClick(it, item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<GiphyDomainModel>() {
    override fun areItemsTheSame(oldItem: GiphyDomainModel, newItem: GiphyDomainModel): Boolean {
        return oldItem?.id == newItem?.id
    }
    override fun areContentsTheSame(oldItem: GiphyDomainModel, newItem: GiphyDomainModel): Boolean {
        return oldItem.id == newItem.id
    }
}
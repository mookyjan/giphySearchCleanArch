package com.mudassir.giphyapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.giphyapi.R
import com.mudassir.giphyapi.databinding.SingleItemGiphyBinding

class GiphyTrendingAdapter : ListAdapter<GiphyDomainModel, GiphyTrendingAdapter.ItemViewHolder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val viewBinding = SingleItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(
            viewBinding
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewHolder(itemView: SingleItemGiphyBinding) : RecyclerView.ViewHolder(itemView.root) {

        private val imageView: ImageView = itemView.ivGiphy

        fun bind(item: GiphyDomainModel) = with(itemView) {
            Glide.with(this).asGif().load(item.url).into(imageView);
            imageView.transitionName = item.url

            setOnClickListener {
                // TODO: Handle on click
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<GiphyDomainModel>() {

    override fun areItemsTheSame(oldItem: GiphyDomainModel, newItem: GiphyDomainModel): Boolean {
        return oldItem?.url == newItem?.url
    }

    override fun areContentsTheSame(oldItem: GiphyDomainModel, newItem: GiphyDomainModel): Boolean {
        return oldItem == newItem
    }
}
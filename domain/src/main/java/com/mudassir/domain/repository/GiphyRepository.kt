package com.mudassir.domain.repository

import androidx.lifecycle.LiveData
import com.mudassir.domain.model.GiphyDomainModel

interface GiphyRepository {

    suspend fun getTrendingGiphy(query: String?): List<GiphyDomainModel>

    suspend fun addToFavourite(giphyDomainModel: GiphyDomainModel)

    fun getFavouriteGiphyList(): LiveData<List<GiphyDomainModel>>

    suspend fun removeFromFavouriteList(giphyDomainModel: GiphyDomainModel)
}
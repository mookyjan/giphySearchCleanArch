package com.mudassir.domain.repository

import androidx.paging.PagingData
import com.mudassir.domain.model.GiphyDomainModel
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {

     fun getTrendingGiphy(query: String?): Flow<PagingData<GiphyDomainModel>>

    suspend fun addToFavourite(giphyDomainModel: GiphyDomainModel)

    suspend fun getFavouriteGiphyList(): List<GiphyDomainModel>

    suspend fun removeFromFavouriteList(giphyDomainModel: GiphyDomainModel)
}
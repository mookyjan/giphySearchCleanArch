package com.mudassir.domain.repository

import com.mudassir.domain.model.GiphyDomainModel

interface GiphyRepository {

    suspend fun getTrendingGiphy(query: String?): List<GiphyDomainModel>

    suspend fun addToFavourite(giphyDomainModel: GiphyDomainModel)

    suspend fun getFavouriteGiphyList(): List<GiphyDomainModel>

    suspend fun removeFromFavouriteList(giphyDomainModel: GiphyDomainModel)
}
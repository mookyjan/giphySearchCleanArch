package com.mudassir.data.datasource.local

import com.mudassir.data.datasource.local.dao.GiphyDao
import com.mudassir.data.datasource.local.model.GiphyEntityModel

internal class GiphyTrendingLocalDataSource(private val giphyDao: GiphyDao) {

    suspend fun getFavouriteGiphyList(): List<GiphyEntityModel> {
        return giphyDao.getAll()
    }

    suspend fun addToFavourite(giphy: GiphyEntityModel) {
        giphyDao.addToFavourite(giphy)
    }

    suspend fun removeFromFavourite(giphy: GiphyEntityModel) {
        giphyDao.removeFromFavourite(giphy)
    }

}
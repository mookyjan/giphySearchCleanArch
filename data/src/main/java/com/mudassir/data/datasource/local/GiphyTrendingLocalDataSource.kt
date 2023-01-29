package com.mudassir.data.datasource.local

import androidx.lifecycle.LiveData
import com.mudassir.data.datasource.local.dao.GiphyDao
import com.mudassir.data.datasource.local.model.GiphyEntityModel

internal class GiphyTrendingLocalDataSource(private val giphyDao: GiphyDao) {

     fun getFavouriteGiphyList(): LiveData<List<GiphyEntityModel>> {
        return giphyDao.getAll()
    }

    suspend fun addToFavourite(giphy: GiphyEntityModel) {
        giphyDao.addToFavourite(giphy)
    }

    suspend fun removeFromFavourite(giphy: GiphyEntityModel) {
        giphyDao.removeFromFavourite(giphy)
    }

}
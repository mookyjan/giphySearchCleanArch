package com.mudassir.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.datasource.local.GiphyTrendingLocalDataSource
import com.mudassir.data.datasource.local.model.GiphyEntityModel
import com.mudassir.data.datasource.local.model.toDomainModel
import com.mudassir.data.datasource.local.model.toEntityModel
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository

internal class GiphyRepositoryImpl(
    private val giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
    private val giphyTrendingLocalDataSource: GiphyTrendingLocalDataSource,
    private val giphyDataToDomainMapper: GiphyDataToDomainMapper
) : GiphyRepository {
    override suspend fun getTrendingGiphy(query: String?): List<GiphyDomainModel> {
        val list = giphyTrendingRemoteDataSource.getTrendingGiphys(query)
        return giphyDataToDomainMapper.invoke(list)
    }

    override  fun getFavouriteGiphyList(): LiveData<List<GiphyDomainModel>> {
        val list = giphyTrendingLocalDataSource.getFavouriteGiphyList()
        val transform: (GiphyEntityModel) -> GiphyDomainModel = { it.toDomainModel() }
        val favList:LiveData<List<GiphyDomainModel>> = Transformations.map(list){
            it.map(transform)
        }
        return favList
    }

    override suspend fun addToFavourite(giphyDomainModel: GiphyDomainModel) {
        val giphyEntityModel = GiphyEntityModel(
            id = giphyDomainModel.title.orEmpty(),
            title = giphyDomainModel.title.orEmpty(),
            rating = giphyDomainModel.rating.orEmpty(),
            url = giphyDomainModel.url.orEmpty(),
            type = giphyDomainModel.type.orEmpty(),
            isFavourite = true
        )
        giphyTrendingLocalDataSource.addToFavourite(giphyEntityModel)
    }

    override suspend fun removeFromFavouriteList(giphyDomainModel: GiphyDomainModel) {
        giphyTrendingLocalDataSource.removeFromFavourite(giphyDomainModel.toEntityModel())
    }
}
package com.mudassir.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mudassir.data.paging.PagingDataSource
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.datasource.local.GiphyTrendingLocalDataSource
import com.mudassir.data.datasource.local.model.GiphyEntityModel
import com.mudassir.data.datasource.local.model.toDomainModel
import com.mudassir.data.datasource.local.model.toEntityModel
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow

internal class GiphyRepositoryImpl(
    private val giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
    private val giphyTrendingLocalDataSource: GiphyTrendingLocalDataSource,
    private val giphyDataToDomainMapper: GiphyDataToDomainMapper
) : GiphyRepository {
    override  fun getTrendingGiphy(query: String?): Flow<PagingData<GiphyDomainModel>> {

        val pager =  Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 20)
        ) {
            PagingDataSource(giphyTrendingRemoteDataSource, query, giphyDataToDomainMapper)
        }.flow
        return pager
    }

    override suspend fun getFavouriteGiphyList(): List<GiphyDomainModel> {
        val list = giphyTrendingLocalDataSource.getFavouriteGiphyList()
        val transform: (GiphyEntityModel) -> GiphyDomainModel = { it.toDomainModel() }
        return list.map(transform)
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
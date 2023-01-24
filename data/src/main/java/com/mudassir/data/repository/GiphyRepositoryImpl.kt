package com.mudassir.data.repository

import com.mudassir.data.datasource.GiphyTrendingRemoteDataSource
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository

class GiphyRepositoryImpl(
    private val remoteGiphyRemoteResponse: GiphyTrendingRemoteDataSource,
    private val giphyDataToDomainMapper: GiphyDataToDomainMapper
) : GiphyRepository {
    override suspend fun getTrendingGiphy(query: String?): List<GiphyDomainModel> {
        val list = remoteGiphyRemoteResponse.getTrendingGiphys(query)
        return giphyDataToDomainMapper.invoke(list)
    }
}
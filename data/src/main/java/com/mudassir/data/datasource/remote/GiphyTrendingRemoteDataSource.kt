package com.mudassir.data.datasource.remote

import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.data.datasource.remote.service.GiphyService

internal class GiphyTrendingRemoteDataSource (private val giphyService: GiphyService) {
    suspend fun getTrendingGiphys(query: String?, limit: Int, offset:Int): GiphyRemoteResponse {
        return if (query.isNullOrBlank()) {
            giphyService.getTrendingGiphys(offset = offset)
        } else {
            giphyService.searchForGiphys(searchInput = query)
        }
    }
}
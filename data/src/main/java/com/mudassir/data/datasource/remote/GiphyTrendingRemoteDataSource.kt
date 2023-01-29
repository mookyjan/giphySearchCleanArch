package com.mudassir.data.datasource.remote

import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.data.datasource.remote.service.GiphyService

internal class GiphyTrendingRemoteDataSource (private val giphyService: GiphyService) {
    suspend fun getTrendingGiphys(query: String?, limit:Int = 10): GiphyRemoteResponse {
        return if (query.isNullOrBlank()) {
            giphyService.getTrendingGiphys(limit = limit)
        } else {
            giphyService.searchForGiphys(searchInput = query)
        }
    }
}
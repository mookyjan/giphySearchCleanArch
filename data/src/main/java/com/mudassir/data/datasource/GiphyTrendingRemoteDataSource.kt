package com.mudassir.data.datasource

import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.data.service.GiphyService
import retrofit2.http.Query

class GiphyTrendingRemoteDataSource (private val giphyService: GiphyService) {

    suspend fun getTrendingGiphys(query: String): GiphyRemoteResponse {
        return giphyService.getTrendingGiphys()
    }
}
package com.mudassir.data.service

import com.mudassir.data.model.GiphyRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query


const val TRENDING_API_END_POINT = "v1/gifs/trending?limit=25&rating=G"

interface GiphyService {


    @GET(TRENDING_API_END_POINT)
    suspend fun getTrendingGiphys(): GiphyRemoteResponse
}
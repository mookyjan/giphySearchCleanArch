package com.mudassir.data.datasource.remote.service

import com.mudassir.data.BuildConfig
import com.mudassir.data.model.GiphyRemoteResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val TRENDING_API_END_POINT = "v1/gifs/trending"
const val GIPHY_SEARCH_API_END_POINT ="v1/gifs/search"
const val LIMIT = 25
const val RATING = "g"

interface GiphyService {

    @GET(TRENDING_API_END_POINT)
    suspend fun getTrendingGiphys(
        @Query("limit") limit :Int = LIMIT,
        @Query("rating") rating: String = RATING,
        @Query("offset") offset: Int
    ): GiphyRemoteResponse

    @GET(GIPHY_SEARCH_API_END_POINT)
    suspend fun searchForGiphys(
        @Query("q") searchInput: String,
        @Query("limit") limit :Int = LIMIT,
        @Query("rating") rating: String = RATING
    ): GiphyRemoteResponse


}
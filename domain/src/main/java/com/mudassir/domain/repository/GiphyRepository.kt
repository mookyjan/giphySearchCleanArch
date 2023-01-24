package com.mudassir.domain.repository

import com.mudassir.domain.model.GiphyDomainModel

interface GiphyRepository {

    suspend fun getTrendingGiphy() : List<GiphyDomainModel>
}
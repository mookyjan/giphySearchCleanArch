package com.mudassir.domain.repository

import com.mudassir.domain.model.GiphyDomainModel

interface GiphyRepository {

    suspend fun getTrendingGiphy(query: String?) : List<GiphyDomainModel>
}
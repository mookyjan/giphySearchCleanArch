package com.mudassir.data.mapper

import com.mudassir.core.DefaultDispatcherProvider
import com.mudassir.core.util.Mapper
import com.mudassir.data.model.GiphyRemoteResponse
import com.mudassir.domain.model.GiphyDomainModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GiphyDataToDomainMapper @Inject constructor() :
    Mapper<GiphyRemoteResponse, List<GiphyDomainModel>> {

    override suspend fun invoke(input: GiphyRemoteResponse): List<GiphyDomainModel> {
        val giphyList = mutableListOf<GiphyDomainModel>()
        withContext(DefaultDispatcherProvider.default()) {
            input.data?.forEach {
                giphyList.add(
                    GiphyDomainModel(
                        id = it.id.orEmpty(),
                        title  = it.title.orEmpty(),
                        rating  = it.rating.orEmpty(),
                        url = it.images?.fixedHeight?.url.orEmpty(),
                        type  = it.type.orEmpty()
                    )
                )
            }
        }
        return giphyList
    }

}
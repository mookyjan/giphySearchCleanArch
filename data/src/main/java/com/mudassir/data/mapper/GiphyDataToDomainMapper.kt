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
                        height = it.images?.fixedHeight?.height.orEmpty(),
                        size = it.images?.fixedHeight?.size.orEmpty(),
                        url = it.images?.fixedHeight?.url.orEmpty(),
                        width = it.images?.fixedHeight?.width.orEmpty()
                    )
                )
            }
        }
        return giphyList
    }

}
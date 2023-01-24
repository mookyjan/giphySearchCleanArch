package com.mudassir.data.mapper

import com.mudassir.core.DefaultDispatcherProvider
import com.mudassir.core.util.Mapper
import com.mudassir.data.model.GiphyData
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
                        height = it.images?.fixed_height?.height.orEmpty(),
                        size = it.images?.fixed_height?.size.orEmpty(),
                        url = it.images?.fixed_height?.url.orEmpty(),
                        width = it.images?.fixed_height?.width.orEmpty()
                    )
                )
            }
        }
        return giphyList
    }

}
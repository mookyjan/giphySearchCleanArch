package com.mudassir.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import com.mudassir.core.Resource
import com.mudassir.core.UseCase
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GiphyTrendingUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository,
    private val errorFactory: ErrorFactory
) : UseCase.UseCaseWithParam<String?, Flow<PagingData<GiphyDomainModel>>> {

    override suspend fun executeAsync(query: String?): Resource<Flow<PagingData<GiphyDomainModel>>> {
        return try {
            val trendingList = giphyRepository.getTrendingGiphy(query)
//            if (trendingList.isEmpty()) return Resource.empty(errorFactory.createEmptyErrorMessage())
            Resource.success(trendingList)
        } catch (ex: Exception) {
            Resource.error(errorFactory.createApiErrorMessage(ex))
        }
    }
}
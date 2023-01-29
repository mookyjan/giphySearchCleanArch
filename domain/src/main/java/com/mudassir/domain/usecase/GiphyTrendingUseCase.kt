package com.mudassir.domain.usecase

import androidx.paging.PagingData
import com.mudassir.core.UseCase
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GiphyTrendingUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository,
    private val errorFactory: ErrorFactory
) : UseCase.UseCaseWithoutResource<String?, Flow<PagingData<GiphyDomainModel>>> {

    override fun executeAsync(query: String?): Flow<PagingData<GiphyDomainModel>> {
        return try {
            val trendingList = giphyRepository.getTrendingGiphy(query = query)
            trendingList
        } catch (ex: Exception) {
            flow { errorFactory.createApiErrorMessage(ex) }
        }
    }
}
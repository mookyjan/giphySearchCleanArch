package com.mudassir.domain.usecase

import com.mudassir.core.Resource
import com.mudassir.core.UseCase
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import javax.inject.Inject

class GiphyTrendingUseCase @Inject constructor(private val giphyRepository: GiphyRepository,
                                               private val errorFactory: ErrorFactory
) : UseCase.UseCaseWithParam<String?,List<GiphyDomainModel>>  {

    override suspend fun executeAsync(query: String?): Resource<List<GiphyDomainModel>> {
        return try {
             val trendingList = giphyRepository.getTrendingGiphy(query)
             if (trendingList.isEmpty())  return Resource.empty(errorFactory.createEmptyErrorMessage())
            //need to map the model to the UI Model
             val mappedList = trendingList
             Resource.success(mappedList)
        } catch (ex: Exception) {
             Resource.error(errorFactory.createApiErrorMessage(ex))
        }
    }



}
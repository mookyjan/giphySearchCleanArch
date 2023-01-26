package com.mudassir.domain.usecase

import android.util.Log
import com.mudassir.core.Resource
import com.mudassir.core.UseCase
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import javax.inject.Inject

class GetFavouriteGiphyUseCase @Inject constructor(private val giphyRepository: GiphyRepository,
                                                   private val errorFactory: ErrorFactory
) : UseCase.UseCaseWithoutParam<List<GiphyDomainModel>> {

    override suspend fun executeAsync(): Resource<List<GiphyDomainModel>> {
        return try {
            val favouriteList = giphyRepository.getFavouriteGiphyList()
            Log.d("UseCaseSuccess", "executeAsync f:  $favouriteList")
            if (favouriteList.isEmpty()) return Resource.empty(errorFactory.createEmptyErrorMessage())
            Resource.success(favouriteList)
        } catch (ex: Exception) {
            Log.d("UseCaseError", "executeAsync:  $ex")
            Resource.error(errorFactory.createApiErrorMessage(ex))
        }
    }
}



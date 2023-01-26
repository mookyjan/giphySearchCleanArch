package com.mudassir.domain.usecase

import android.util.Log
import com.mudassir.core.Resource
import com.mudassir.core.UseCase
import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository,
    private val errorFactory: ErrorFactory
) : UseCase.UseCaseWithParam<GiphyDomainModel, Unit> {

    override suspend fun executeAsync(query: GiphyDomainModel?): Resource<Unit> {
        return try {
            query?.let {
                giphyRepository.addToFavourite(it)
                Resource.success(Unit)
            }
            Resource.error("Error")
        } catch (ex: Exception) {
            Log.d("UseCaseError", "executeAsync:  $ex")
            Resource.error(errorFactory.createApiErrorMessage(ex))
        }
    }

}
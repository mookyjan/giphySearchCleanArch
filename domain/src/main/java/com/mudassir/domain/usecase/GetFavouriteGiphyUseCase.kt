package com.mudassir.domain.usecase

import androidx.lifecycle.LiveData
import com.mudassir.core.UseCase
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.repository.GiphyRepository
import javax.inject.Inject

class GetFavouriteGiphyUseCase @Inject constructor(
    private val giphyRepository: GiphyRepository
) : UseCase.UseCaseWithoutParamResource<LiveData<List<GiphyDomainModel>>> {

    override fun execute(): LiveData<List<GiphyDomainModel>> {
        return giphyRepository.getFavouriteGiphyList()
    }
}



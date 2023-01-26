package com.mudassir.giphyapi.ui.viewModel

import androidx.lifecycle.*
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.giphyapi.util.Constants.SAVED_QUERY_KEY
import kotlinx.coroutines.launch

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase,
    private val favouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel() {

    fun onEnter(query: String? = (state.get<String>(SAVED_QUERY_KEY)) ?: "") {
        giphyLiveDataEvent.value = query
    }

    val giphyLiveDataEvent = MutableLiveData<String?>()
    val giphyLiveData: LiveData<Resource<List<GiphyDomainModel>>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query)
                emit(result)
            }
        }

    var addToFavouriteEvent = MutableLiveData<Unit>()
    private val favouriteList: LiveData<Resource<List<GiphyDomainModel>>> =
        addToFavouriteEvent.switchMap {
            liveData {
                emit(Resource.loading(null))
                val result = favouriteGiphyUseCase.executeAsync()
                emit(result)
            }
        }

    fun getFavList(): LiveData<Resource<List<GiphyDomainModel>>> {
        return favouriteList
    }

    fun addToFavourite(domainModel: GiphyDomainModel) {
        viewModelScope.launch {
            addToFavouriteUseCase.executeAsync(domainModel)
        }
    }
}
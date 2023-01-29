package com.mudassir.giphyapi.ui.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.util.query
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.domain.usecase.RemoveFromFavouriteUseCase
import com.mudassir.giphyapi.util.Constants.SAVED_QUERY_KEY
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase,
    private val favouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase
) : ViewModel() {

    val giphyLiveDataEvent = MutableLiveData<String?>()
    fun onEnter(query: String? = (state.get<String>(SAVED_QUERY_KEY)) ?: "") {
        giphyLiveDataEvent.value = query
    }

    init {
        onEnter()
    }

    val giphyFlowList : LiveData<PagingData<GiphyDomainModel>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            getGiphyList().asLiveData()
        }



//    val giphyLiveDataEvent = MutableLiveData<String?>()
//

    fun getGiphyList(): Flow<PagingData<GiphyDomainModel>> {
        return trendingUseCase.executeAsync(giphyLiveDataEvent.value.toString()).cachedIn(viewModelScope)
    }

    val giphyLiveData: LiveData<Resource<Flow<PagingData<GiphyDomainModel>>>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query).cachedIn(viewModelScope)
                emit(Resource.success(result))
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

    fun removeFromFavourite(domainModel: GiphyDomainModel) {
        viewModelScope.launch {
            removeFromFavouriteUseCase.executeAsync(domainModel)
        }
    }
}
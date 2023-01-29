package com.mudassir.giphyapi.ui.viewModel

import androidx.lifecycle.*
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.domain.usecase.RemoveFromFavouriteUseCase
import com.mudassir.giphyapi.util.Constants.SAVED_QUERY_KEY
import kotlinx.coroutines.launch

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase,
    private val favouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase
) : ViewModel() {

    val giphyLiveDataEvent = MutableLiveData<String?>()

    init {
        onEnter()
    }

    /**
     * method to be called when user enter query for search
     */
    fun onEnter(query: String? = (state.get<String>(SAVED_QUERY_KEY)) ?: "") {
        giphyLiveDataEvent.value = query
    }

    val giphyLiveData: LiveData<Resource<List<GiphyDomainModel>>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query)
                emit(result)
            }
        }

    /**
     * get list of favourite gifs
     */
    val favouriteList: LiveData<List<GiphyDomainModel>> = favouriteGiphyUseCase.execute()

    /**
     * add gifs to favourite
     */
    fun addToFavourite(domainModel: GiphyDomainModel) {
        viewModelScope.launch {
            addToFavouriteUseCase.executeAsync(domainModel)
        }
    }

    /**
     * remove gifs from favourite list
     */
    fun removeFromFavourite(domainModel: GiphyDomainModel) {
        viewModelScope.launch {
            removeFromFavouriteUseCase.executeAsync(domainModel)
        }
    }
}
package com.mudassir.giphyapi.ui.viewModel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.domain.usecase.RemoveFromFavouriteUseCase
import com.mudassir.giphyapi.util.Constants.SAVED_QUERY_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase,
    private val favouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeFromFavouriteUseCase: RemoveFromFavouriteUseCase
) : ViewModel() {

    //variable to hold data of search query
    val giphyLiveDataEvent = MutableLiveData<String?>()

    /**
     * function to be called when user enter the search query
     */
    fun onEnter(query: String? = (state.get<String>(SAVED_QUERY_KEY)) ?: "") {
        giphyLiveDataEvent.value = query
    }

    init {
        onEnter()
    }

    /**
     * setup to get the list of giphy
     */
    val giphyLiveData: LiveData<Resource<Flow<PagingData<GiphyDomainModel>>>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query).cachedIn(viewModelScope)
                result.catch {
                    emit(Resource.error("${it.localizedMessage}"))
                }
                emit(Resource.success(result))
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
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
    val addToFavouriteEvent = MutableLiveData<Unit>()
    init {
        onEnter()
//       addToFavouriteEvent.value = Unit
    }
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

//    private val favouriteList: LiveData<Resource<List<GiphyDomainModel>>> =
//        addToFavouriteEvent.switchMap {
//            liveData {
//                emit(Resource.loading(null))
//                val result = favouriteGiphyUseCase.executeAsync()
//                emit(result)
//            }
//        }

    val favouriteList: LiveData<List<GiphyDomainModel>> = favouriteGiphyUseCase.execute()


//    fun getFavList(): LiveData<Resource<List<GiphyDomainModel>>> {
//        return favouriteList
//    }

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
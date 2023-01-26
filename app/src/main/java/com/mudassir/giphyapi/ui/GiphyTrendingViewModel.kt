package com.mudassir.giphyapi.ui

import androidx.lifecycle.*
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.AddToFavouriteUseCase
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.giphyapi.Constants
import com.mudassir.giphyapi.Constants.SAVED_QUERY_KEY
import com.mudassir.giphyapi.ui.model.GiphyUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase,
    private val favouriteGiphyUseCase: GetFavouriteGiphyUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel() {

    fun onEnter(query: String? = (state.get(SAVED_QUERY_KEY) as? String) ?: "") {
        giphyLiveDataEvent.value = query
    }

     val giphyLiveDataEvent = MutableLiveData<String>()
    val giphyLiveData: LiveData<Resource<List<GiphyDomainModel>>> =
        giphyLiveDataEvent.switchMap { query ->
            state[SAVED_QUERY_KEY] = query
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query)
                emit(result)
            }
        }


    val favouriteList : LiveData<Resource<List<GiphyDomainModel>>> =
        liveData {
            emit(Resource.loading(null))
            val result = favouriteGiphyUseCase.executeAsync()
            emit(result)
//            val ma = result.data?.map {
//                giphyDomainModel ->
//                GiphyUiModel(title = giphyDomainModel.title,
//                    rating = giphyDomainModel.rating,
//                    url = giphyDomainModel.url)
//            }
//           ma?.let {
//               emit(it)
//           }
        }

    var addTofavourite = MutableLiveData<GiphyDomainModel>()
    val addToFavouriteEvent : LiveData<Resource<Unit>> =
        addTofavourite.switchMap {
            liveData {
                Resource.loading(null)
                addToFavouriteUseCase.executeAsync(it)
            }
        }

    fun addTofav(domainModel: GiphyDomainModel){
        viewModelScope.launch {
            addToFavouriteUseCase.executeAsync(domainModel)
        }
    }
}
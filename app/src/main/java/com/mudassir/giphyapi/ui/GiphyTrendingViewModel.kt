package com.mudassir.giphyapi.ui

import androidx.lifecycle.*
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import com.mudassir.giphyapi.Constants
import com.mudassir.giphyapi.Constants.SAVED_QUERY_KEY
import javax.inject.Inject

class GiphyTrendingViewModel constructor(
    private val state: SavedStateHandle,
    private val trendingUseCase: GiphyTrendingUseCase
) : ViewModel() {

    val query: MutableLiveData<String> = MutableLiveData("")

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
}
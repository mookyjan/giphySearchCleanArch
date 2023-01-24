package com.mudassir.giphyapi.ui

import androidx.lifecycle.*
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import javax.inject.Inject

class GiphyTrendingViewModel @Inject constructor(
    private val trendingUseCase: GiphyTrendingUseCase
) : ViewModel() {

    val query: MutableLiveData<String> = MutableLiveData("")
    companion object {
        private const val SAVED_QUERY_KEY = "query"
    }

    fun onEnter(query: String? = null) {
        giphyLiveDataEvent.value = query
    }

    val giphyLiveDataEvent = MutableLiveData<String>()
    val giphyLiveData: LiveData<Resource<List<GiphyDomainModel>>> =
        giphyLiveDataEvent.switchMap { query ->
            liveData {
                emit(Resource.loading(null))
                val result = trendingUseCase.executeAsync(query)
                emit(result)
            }
        }
}
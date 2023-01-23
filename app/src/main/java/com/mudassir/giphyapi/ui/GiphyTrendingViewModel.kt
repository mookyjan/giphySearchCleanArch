package com.mudassir.giphyapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mudassir.core.Resource
import com.mudassir.domain.model.GiphyDomainModel
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class GiphyTrendingViewModel @Inject constructor (private val trendingUseCase: GiphyTrendingUseCase) : ViewModel() {


    private val _giphyLiveData = MutableLiveData<Resource<List<GiphyDomainModel>>>()
    val giphyLiveData: LiveData<Resource<List<GiphyDomainModel>>> get() = _giphyLiveData




    fun callApi() {
        viewModelScope.launch{
           val trending = trendingUseCase.executeAsync()
            _giphyLiveData.postValue(trending)
        }

    }
}
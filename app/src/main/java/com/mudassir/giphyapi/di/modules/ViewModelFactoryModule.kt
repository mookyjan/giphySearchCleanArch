package com.mudassir.giphyapi.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mudassir.giphyapi.ui.GiphyTrendingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GiphyTrendingViewModel::class)
    abstract fun provideTrendingGiphyViewModel(viewModel: GiphyTrendingViewModel) : ViewModel


}
package com.mudassir.giphyapi.di.modules

import androidx.lifecycle.ViewModel
import com.mudassir.giphyapi.ui.viewModel.GiphyTrendingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GiphyTrendingViewModel::class)
    abstract fun provideTrendingGiphyViewModel(viewModel: GiphyTrendingViewModel) : ViewModel


}
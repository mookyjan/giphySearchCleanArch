package com.mudassir.domain.di

import com.mudassir.core.util.ErrorFactory
import com.mudassir.domain.repository.GiphyRepository
import com.mudassir.domain.usecase.GetFavouriteGiphyUseCase
import com.mudassir.domain.usecase.GiphyTrendingUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideTrendingGiphyUseCase(giphyRepository: GiphyRepository, errorFactory: ErrorFactory) =
        GiphyTrendingUseCase(giphyRepository, errorFactory)

    @Provides
    fun provideFavouriteUseCase(giphyRepository: GiphyRepository, errorFactory: ErrorFactory) =
        GetFavouriteGiphyUseCase(giphyRepository, errorFactory)
}
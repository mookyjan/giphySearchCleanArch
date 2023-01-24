package com.mudassir.data.di

import com.mudassir.data.datasource.GiphyTrendingRemoteDataSource
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.data.repository.GiphyRepositoryImpl
import com.mudassir.data.service.GiphyService
import com.mudassir.domain.repository.GiphyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideGiphyMapper() = GiphyDataToDomainMapper()


    @Provides
    fun provideGiphyRemoteDataSource(giphyService: GiphyService) =
        GiphyTrendingRemoteDataSource(giphyService)


    @Provides
    fun provideGiphyRepository(
        giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
        giphyDataToDomainMapper: GiphyDataToDomainMapper
    ): GiphyRepository =
        GiphyRepositoryImpl(giphyTrendingRemoteDataSource, giphyDataToDomainMapper)
}



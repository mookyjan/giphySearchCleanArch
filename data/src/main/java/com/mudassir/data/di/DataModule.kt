package com.mudassir.data.di

import android.content.Context
import com.mudassir.data.datasource.remote.GiphyTrendingRemoteDataSource
import com.mudassir.data.datasource.local.GiphyTrendingLocalDataSource
import com.mudassir.data.datasource.local.dao.GiphyDao
import com.mudassir.data.datasource.local.db.GiphyDatabase
import com.mudassir.data.mapper.GiphyDataToDomainMapper
import com.mudassir.data.repository.GiphyRepositoryImpl
import com.mudassir.data.datasource.remote.service.GiphyService
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
    internal fun provideGiphyDatabase(context: Context) = GiphyDatabase.newInstance(context)

    @Provides
    internal fun provideGiphyLocalDataSource(giphyDao: GiphyDao) =
        GiphyTrendingLocalDataSource(giphyDao)

    @Provides
    internal fun provideGiphyDao(database: GiphyDatabase): GiphyDao = database.giphys()


    @Provides
    internal fun provideGiphyRepository(
        giphyTrendingRemoteDataSource: GiphyTrendingRemoteDataSource,
        giphyTrendingLocalDataSource: GiphyTrendingLocalDataSource,
        giphyDataToDomainMapper: GiphyDataToDomainMapper
    ): GiphyRepository =
        GiphyRepositoryImpl(giphyTrendingRemoteDataSource, giphyTrendingLocalDataSource, giphyDataToDomainMapper)
}



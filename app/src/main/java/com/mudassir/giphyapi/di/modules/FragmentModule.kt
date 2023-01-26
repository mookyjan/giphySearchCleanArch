package com.mudassir.giphyapi.di.modules

import com.mudassir.giphyapi.ui.fragments.FavouritesFragment
import com.mudassir.giphyapi.ui.fragments.GiphyTrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun giphyTrendingFragment() : GiphyTrendingFragment

    @ContributesAndroidInjector
    abstract fun getFavouritesFragment(): FavouritesFragment
}
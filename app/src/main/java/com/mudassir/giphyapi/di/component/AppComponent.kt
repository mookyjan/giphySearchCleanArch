package com.mudassir.giphyapi.di.component

import android.app.Application
import android.content.Context
import com.mudassir.core.BaseModule
import com.mudassir.data.di.DataModule
import com.mudassir.data.util.NetworkModule
import com.mudassir.domain.di.DomainModule
import com.mudassir.giphyapi.GiphyApp
import com.mudassir.giphyapi.di.modules.AppModule
import com.mudassir.giphyapi.di.modules.FragmentModule
import com.mudassir.giphyapi.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,
    AndroidInjectionModule::class,
    ViewModelFactoryModule::class, BaseModule::class,
    FragmentModule::class,
    NetworkModule::class,
    DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(application: GiphyApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}
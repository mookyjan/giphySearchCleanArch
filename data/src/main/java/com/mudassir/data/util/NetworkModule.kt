package com.mudassir.data.util


import com.mudassir.data.BuildConfig
import com.mudassir.data.service.GiphyService
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideGiphyServiceApi(retrofit: Retrofit): GiphyService
            = retrofit.create(GiphyService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {

        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getHttpLoggingInterceptor())
        }

        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)

        builder.addInterceptor(RequestInterceptor())

        return builder.build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory
            = MoshiConverterFactory.create(moshi)

    @Provides
    fun provideMoshi(): Moshi
            = Moshi.Builder()
        .add(Wrapped.ADAPTER_FACTORY)
        .add(KotlinJsonAdapterFactory())
        .build()


    @Provides
    @Singleton
    fun getUrl():String{
        return  BuildConfig.baseUrl
    }

}

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        //add api key here to every request
        val url = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}
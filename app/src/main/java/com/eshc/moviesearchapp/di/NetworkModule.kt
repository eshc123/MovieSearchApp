package com.eshc.moviesearchapp.di

import com.eshc.moviesearchapp.BuildConfig
import com.eshc.moviesearchapp.data.api.NaverInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter : Converter.Factory
    ) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.NAVER_BASE_URL)
            .addConverterFactory(converter)
            .build()
    }

    @Provides
    @Singleton
    fun provideConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        naverInterceptor : NaverInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .addNetworkInterceptor(naverInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideNaverInterceptor() : NaverInterceptor {
        return NaverInterceptor()
    }

}
package com.eshc.moviesearchapp.di

import com.eshc.moviesearchapp.data.api.NaverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideNaverMovieService(
        retrofit: Retrofit
    ) : NaverService {
        return retrofit.create(NaverService::class.java)
    }
}
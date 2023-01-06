package com.eshc.moviesearchapp.di

import com.eshc.moviesearchapp.data.MovieRepository
import com.eshc.moviesearchapp.data.RecentRepository
import com.eshc.moviesearchapp.data.repository.MovieRepositoryImpl
import com.eshc.moviesearchapp.data.repository.RecentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieRepository(
        movieRepository: MovieRepositoryImpl
    ) : MovieRepository

    @Binds
    abstract fun bindRecentRepository(
        recentRepository: RecentRepositoryImpl
    ) : RecentRepository
}
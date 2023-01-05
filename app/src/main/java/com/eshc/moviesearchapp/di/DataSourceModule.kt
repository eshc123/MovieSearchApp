package com.eshc.moviesearchapp.di

import com.eshc.moviesearchapp.data.source.MovieDataSource
import com.eshc.moviesearchapp.data.source.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieDataSource(
        movieDataSource: MovieRemoteDataSourceImpl
    ) : MovieDataSource
}
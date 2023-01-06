package com.eshc.moviesearchapp.di

import android.content.Context
import androidx.room.Room
import com.eshc.moviesearchapp.MovieApp
import com.eshc.moviesearchapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideRecentDao(
        database: AppDatabase
    ) = database.recentDao()
}
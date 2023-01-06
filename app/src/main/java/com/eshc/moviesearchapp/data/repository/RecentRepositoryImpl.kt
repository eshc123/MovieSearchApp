package com.eshc.moviesearchapp.data.repository

import com.eshc.moviesearchapp.data.RecentRepository
import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import com.eshc.moviesearchapp.data.source.RecentDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentRepositoryImpl @Inject constructor(
    private val recentDataSource: RecentDataSource
) : RecentRepository {
    override suspend fun insertRecentEntity(recentEntity: RecentEntity): Long {
        return recentDataSource.insertRecentEntity(recentEntity)
    }

    override fun getRecentEntities(): Flow<List<RecentEntity>> {
        return recentDataSource.getRecentEntities()
    }

}
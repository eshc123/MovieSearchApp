package com.eshc.moviesearchapp.data.source.local

import com.eshc.moviesearchapp.data.db.dao.RecentDao
import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import com.eshc.moviesearchapp.data.source.RecentDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentLocalDataSourceImpl @Inject constructor(
    private val recentDao: RecentDao
) : RecentDataSource {
    override suspend fun insertRecentEntity(recentEntity: RecentEntity): Long {
        return recentDao.insertRecentEntity(recentEntity)
    }

    override fun getRecentEntities(): Flow<List<RecentEntity>> {
        return recentDao.getRecentEntities()
    }

}
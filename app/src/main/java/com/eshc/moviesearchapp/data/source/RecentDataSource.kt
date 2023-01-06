package com.eshc.moviesearchapp.data.source

import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import kotlinx.coroutines.flow.Flow

interface RecentDataSource {
    suspend fun insertRecentEntity(recentEntity: RecentEntity) : Long

    fun getRecentEntities() : Flow<List<RecentEntity>>
}
package com.eshc.moviesearchapp.data

import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import kotlinx.coroutines.flow.Flow

interface RecentRepository {
    suspend fun insertRecentEntity(recentEntity: RecentEntity) : Long

    fun getRecentEntities() : Flow<List<RecentEntity>>
}
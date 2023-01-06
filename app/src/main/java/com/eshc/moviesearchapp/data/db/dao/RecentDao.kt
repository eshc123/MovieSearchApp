package com.eshc.moviesearchapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentEntity(recentEntity: RecentEntity) : Long

    @Query("SELECT * FROM recent_table ORDER BY updatedAt DESC LIMIT 10")
    fun getRecentEntities() : Flow<List<RecentEntity>>
}
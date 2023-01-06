package com.eshc.moviesearchapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eshc.moviesearchapp.data.db.dao.RecentDao
import com.eshc.moviesearchapp.data.db.entity.RecentEntity

@Database(entities = arrayOf(RecentEntity::class), version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(){

    abstract fun recentDao() : RecentDao

    companion object {
        const val DATABASE_NAME = "database.db"
        const val DATABASE_VERSION = 1
    }
}
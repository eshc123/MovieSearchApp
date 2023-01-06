package com.eshc.moviesearchapp.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ("recent_table"))
data class RecentEntity (
    @PrimaryKey
    @ColumnInfo(name = "recent_word") val recentWord : String,
    @ColumnInfo(name = "updatedAt") val updatedAt: Long
)
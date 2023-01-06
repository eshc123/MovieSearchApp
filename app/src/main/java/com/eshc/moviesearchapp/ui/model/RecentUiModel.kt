package com.eshc.moviesearchapp.ui.model

import com.eshc.moviesearchapp.data.db.entity.RecentEntity

data class RecentUiModel(
    val title : String,
    val updatedAt : Long
)

fun RecentEntity.toUiModel() = RecentUiModel(
    title = recentWord,
    updatedAt = updatedAt
)
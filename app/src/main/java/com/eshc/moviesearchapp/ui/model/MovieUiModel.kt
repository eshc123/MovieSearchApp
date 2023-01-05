package com.eshc.moviesearchapp.ui.model

import com.eshc.moviesearchapp.data.api.model.Movie

data class MovieUiModel(
    val title : String,
    val publishDate : String,
    val image : String,
    val userRating : Float,
    val link : String
)

fun Movie.toUiModel() =
    MovieUiModel(
        title = title,
        publishDate = pubDate,
        image = image,
        userRating = userRating,
        link = link
    )

package com.eshc.moviesearchapp.data

import com.eshc.moviesearchapp.data.api.model.Channel

interface MovieRepository {
    suspend fun getMoviesByQuery(query : String, start : Int,pagingSize : Int) : Result<Channel>
}
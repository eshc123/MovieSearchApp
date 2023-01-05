package com.eshc.moviesearchapp.data.source

import com.eshc.moviesearchapp.data.api.model.Channel

interface MovieDataSource {
    suspend fun getMoviesByQuery(query : String, start : Int, pageSize : Int) : Result<Channel>
}
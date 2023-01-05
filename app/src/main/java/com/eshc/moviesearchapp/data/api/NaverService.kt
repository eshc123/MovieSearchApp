package com.eshc.moviesearchapp.data.api

import com.eshc.moviesearchapp.data.api.model.Channel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverService {

    @GET("search/movie.json")
    suspend fun getMoviesByQuery(
        @Query("query") query : String,
        @Query("start") start : Int,
        @Query("display") display : Int
    ) : Response<Channel>
}
package com.eshc.moviesearchapp.data.source.remote

import com.eshc.moviesearchapp.data.api.NaverService
import com.eshc.moviesearchapp.data.api.model.Channel
import com.eshc.moviesearchapp.data.source.MovieDataSource
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val naverService: NaverService
) : MovieDataSource {

    override suspend fun getMoviesByQuery(query: String, start : Int, pageSize : Int): Result<Channel> {
        return try {
            val response = naverService.getMoviesByQuery(query,start,pageSize)
            if(response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception())
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}
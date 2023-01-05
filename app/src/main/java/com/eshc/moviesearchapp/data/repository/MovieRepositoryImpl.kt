package com.eshc.moviesearchapp.data.repository

import com.eshc.moviesearchapp.data.MovieRepository
import com.eshc.moviesearchapp.data.api.model.Channel
import com.eshc.moviesearchapp.data.source.MovieDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {

    override suspend fun getMoviesByQuery(query: String, start: Int,pagingSize : Int): Result<Channel> {
        return try {
            val result = movieDataSource.getMoviesByQuery(query,start, pagingSize).getOrThrow()
            Result.success(result)
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}
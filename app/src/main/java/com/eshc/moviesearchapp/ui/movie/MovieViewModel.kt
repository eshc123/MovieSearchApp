package com.eshc.moviesearchapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.moviesearchapp.data.MovieRepository
import com.eshc.moviesearchapp.ui.model.MovieUiModel
import com.eshc.moviesearchapp.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val query = MutableLiveData("")

    private var _movies = MutableLiveData<List<MovieUiModel>>(emptyList())
    val movies: LiveData<List<MovieUiModel>>
        get() = _movies

    private var page = 1
    private var pagingSize = 10
    private var pagingEnded = false
    var loading = false

    fun setMovies() {
        page = 1
        pagingEnded = false
        loading = true
        viewModelScope.launch {
            getMoviesByQuery().let {
                _movies.value = it
            }
        }
    }

    fun addMovies() {
        loading = true
        viewModelScope.launch {
            getMoviesByQuery().let {
                if(it.isNotEmpty()) _movies.value = (movies.value ?: emptyList()) + it
            }
        }
    }


    private suspend fun getMoviesByQuery(): List<MovieUiModel> = withContext(Dispatchers.IO) {
        try {
            println(movies.value?.size)
            if(pagingEnded.not()) {
                movieRepository.getMoviesByQuery(query.value ?: "", page,pagingSize).getOrThrow().let { channel ->
                    checkPage(channel.start, channel.total ,pagingSize)
                    loading = false
                    page += pagingSize
                        channel.items.map { movie ->
                        movie.toUiModel()
                    }
                }
            } else {
                loading = false
                emptyList()
            }
        } catch (e: Exception) {
            loading = false
            emptyList<MovieUiModel>()
        }
    }
    private fun checkPage(start : Int, total : Int, pagingSize: Int) {
        pagingEnded = start + pagingSize >= total
    }
}


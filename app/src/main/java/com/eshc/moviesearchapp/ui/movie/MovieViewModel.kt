package com.eshc.moviesearchapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eshc.moviesearchapp.data.MovieRepository
import com.eshc.moviesearchapp.data.RecentRepository
import com.eshc.moviesearchapp.data.db.entity.RecentEntity
import com.eshc.moviesearchapp.ui.model.MovieUiModel
import com.eshc.moviesearchapp.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val recentRepository: RecentRepository
) : ViewModel() {

    val query = MutableLiveData("")

    private var _movies = MutableLiveData<List<MovieUiModel>>(emptyList())
    val movies: LiveData<List<MovieUiModel>>
        get() = _movies

    val recents = recentRepository.getRecentEntities().map { recentEntityList ->
        recentEntityList.map { recentEntity ->
            recentEntity.toUiModel()
        }
    }

    private var page = 1
    private var pagingSize = 10
    private var pagingEnded = false
    val loading = MutableLiveData<Boolean>(false)

    fun setMovies() {
        page = 1
        pagingEnded = false
        loading.value = true
        viewModelScope.launch {
            getMoviesByQuery().let { movieList ->
                _movies.value = movieList
            }
            addRecent()
        }
    }

    fun addMovies() {
        loading.value = true
        viewModelScope.launch {
            getMoviesByQuery().let { movieList ->
                if (movieList.isNotEmpty()) _movies.value =
                    (movies.value ?: emptyList()) + movieList
            }
        }
    }


    private suspend fun getMoviesByQuery(): List<MovieUiModel> = withContext(Dispatchers.IO) {
        try {
            if (pagingEnded.not()) {
                movieRepository.getMoviesByQuery(getTrimmedQuery(), page, pagingSize).getOrThrow()
                    .let { channel ->
                        checkPage(channel.start, channel.total, pagingSize)
                        page += pagingSize
                        channel.items
                            .map { movie ->
                                movie.toUiModel()
                            }
                    }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        } finally {
            loading.postValue(false)
        }
    }

    private fun getTrimmedQuery() = query.value.toString().trim()

    private fun checkPage(start: Int, total: Int, pagingSize: Int) {
        pagingEnded = start + pagingSize >= total
    }

    private suspend fun addRecent() {
        recentRepository.insertRecentEntity(
            RecentEntity(
                getTrimmedQuery(),
                System.currentTimeMillis()
            )
        )
    }
}


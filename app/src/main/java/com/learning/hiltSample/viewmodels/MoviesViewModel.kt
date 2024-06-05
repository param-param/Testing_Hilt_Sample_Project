package com.learning.hiltSample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.hiltSample.models.Movie
import com.learning.hiltSample.repository.MoviesRepository
import com.learning.hiltSample.repository.ResponseType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getMovies()
        }
    }

    val movies: LiveData<ResponseType<List<Movie>>>
        get() = repository.movies
}
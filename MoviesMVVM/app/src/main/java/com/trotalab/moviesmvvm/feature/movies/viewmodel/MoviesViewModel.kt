package com.trotalab.moviesmvvm.feature.movies.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.trotalab.moviesmvvm.data.MovieDetails
import com.trotalab.moviesmvvm.data.repository.MoviesRepository
import com.vn.fsoft.gstlib.core.BaseViewModel
import com.vn.fsoft.gstlib.core.network.api.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MoviesViewModel @ViewModelInject constructor(private val mainRepository: MoviesRepository) :
    BaseViewModel() {

    private val _movies: MutableLiveData<Resource<List<MovieDetails>>> = MutableLiveData()
    val movies: LiveData<Resource<List<MovieDetails>>> = _movies

    fun fetchData() {
        viewModelScope.launch {
            mainRepository.getAllMoviesFromRemote().onEach { dataState ->
                _movies.value = dataState
            }.launchIn(viewModelScope)
        }
    }
}
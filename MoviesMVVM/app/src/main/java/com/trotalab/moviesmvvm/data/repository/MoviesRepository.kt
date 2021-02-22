package com.trotalab.moviesmvvm.data.repository

import com.trotalab.moviesmvvm.data.MovieDetails
import com.trotalab.moviesmvvm.data.datasource.MoviesApi
import com.vn.fsoft.gstlib.core.network.api.Resource
import com.vn.fsoft.gstlib.core.network.api.requestApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val employeeCalls: MoviesApi
) {

    suspend fun getAllMoviesFromRemote(): Flow<Resource<List<MovieDetails>>> = flow {
        emit(requestApi(request = { employeeCalls.movies() }))
    }
}
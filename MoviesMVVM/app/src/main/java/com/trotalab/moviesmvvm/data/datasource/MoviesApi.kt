package com.trotalab.moviesmvvm.data.datasource

import com.trotalab.moviesmvvm.data.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {

    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
    }

    @GET("movies.json")
    suspend fun movies(): Response<List<MovieDetails>>

    @GET("movie_0{$PARAM_MOVIE_ID}.json")
    suspend fun movieDetails(@Path(PARAM_MOVIE_ID) movieId: Int): Response<MovieDetails>

}
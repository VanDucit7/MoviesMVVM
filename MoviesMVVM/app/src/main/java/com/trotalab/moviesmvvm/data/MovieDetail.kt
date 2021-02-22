package com.trotalab.moviesmvvm.data

data class MovieDetails(
    var id: Int = 0,
    var title: String = "",
    var poster: String = "",
    var summary: String = "",
    var cast: String = "",
    var director: String = "",
    var year: Int = 0,
    var trailer: String = ""
) {

    companion object {
        val empty = MovieDetails(0, "", "", "", "", "", 0, "")
    }
}
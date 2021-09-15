package com.example.domain.entities

data class MovieDetail (

    val response: String?,
    val website: String?,
    val production: String?,
    val boxoffice: String?,
    val dvd: String?,
    val type: String?,
    val imdbid: String?,
    val imdbvotes: String?,
    val imdbrating: String?,
    val  metascore: String?,
    val ratings: List<Ratings>?,
    val poster: String?,
    val awards: String?,
    val country: String?,
    val language: String?,
    val plot: String?,
    val actors: String?,
    val writer: String?,
    val genre: String?,
    val runtime: String?,
    val released: String?,
    val rated: String?,
    val year: String?,
    val title: String?
    ) {
    data class Ratings (
        var value: String?,
        var source: String?
    )


}





package com.example.domain.entities

data class SearchResults(
    val response: String?,
    val totalResults: String?,
    val search: List<SearchItem>?
) {
    class SearchItem (
        val type: String?,
        val year: String?,
        var imdbID: String?,
        var poster: String?,
        var title: String?
    )
}
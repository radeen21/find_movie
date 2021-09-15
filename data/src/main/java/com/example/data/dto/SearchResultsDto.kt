package com.example.data.dto

import com.google.gson.annotations.SerializedName

class SearchResultsDto {

    @SerializedName("Response")
    val response: String? = null
    @SerializedName("totalResults")
    val totalResults: String? = null
    @SerializedName("Search")
    val search: List<SearchItem>? = null

//
//    fun setResponse(response: String) {
//        this.response = response
//    }
//
//    fun getResponse(): String? {
//        return response
//    }
//
//    fun setTotalResults(totalResults: String) {
//        this.totalResults = totalResults
//    }
//
//    fun getTotalResults(): String? {
//        return totalResults
//    }
//
//    fun setSearch(search: List<SearchItem>) {
//        this.search = search
//    }
//
//    fun getSearch(): List<SearchItem>? {
//        return search
//    }

//    override fun toString(): String {
//        return "Response{" +
//                "response = '" + response + '\''.toString() +
//                ",totalResults = '" + totalResults + '\''.toString() +
//                ",search = '" + search + '\''.toString() +
//                "}"
//    }

    class SearchItem {

        @SerializedName("Type")
        val type: String? = null

        @SerializedName("Year")
        val year: String? = null

        @SerializedName("imdbID")
        var imdbID: String? = null

        @SerializedName("Poster")
        val poster: String? = null

        @SerializedName("Title")
        val title: String? = null

        override fun toString(): String {
            return "SearchItem{" +
                    "type = '" + type + '\''.toString() +
                    ",year = '" + year + '\''.toString() +
                    ",imdbID = '" + imdbID + '\''.toString() +
                    ",poster = '" + poster + '\''.toString() +
                    ",title = '" + title + '\''.toString() +
                    "}"
        }
    }
}
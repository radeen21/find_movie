package com.example.domain.repository

import com.example.domain.entities.MovieDetail
import com.example.domain.entities.SearchResults


interface MovieRepository {
    suspend fun getSearchResultData(searchTitle: String, apiKey: String, pageIndex: Int): SearchResults
    suspend fun getMovieDetailData(title: String, apiKey: String): MovieDetail
}
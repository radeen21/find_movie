package com.example.data.repoimpl

import com.example.base.apis.MovieApi
import com.example.domain.entities.MovieDetail
import com.example.domain.entities.SearchResults
import com.example.domain.repository.MovieRepository
import com.example.data.mapper.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(private val api: MovieApi) : MovieRepository {

    override suspend fun getSearchResultData(
        searchTitle: String,
        apiKey: String,
        pageIndex: Int
    ): SearchResults =
        api.getSearchResultData(searchTitle, apiKey, pageIndex).map()


    override suspend fun getMovieDetailData(title: String, apiKey: String): MovieDetail {
        TODO("Not yet implemented")
    }
}
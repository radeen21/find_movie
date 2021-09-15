package com.example.domain.interactor

import com.example.domain.CoroutineUseCase
import com.example.domain.entities.SearchResults
import com.example.domain.repository.MovieRepository
import java.lang.Exception
import javax.inject.Inject

class GetSearchMovie @Inject constructor(private val repository: MovieRepository):
    CoroutineUseCase<SearchResults, GetSearchMovie.Params>() {


    override suspend fun build(param: Params?): SearchResults =
         repository.getSearchResultData(params?.searchTitle ?: throw Exception("Required Title"),
            params?.apiKey ?: throw Exception("Required anyQuery"),
            params?.pageIndex ?: throw Exception("Required page"))

        data class Params(val searchTitle: String, val apiKey: String, val pageIndex: Int)


}
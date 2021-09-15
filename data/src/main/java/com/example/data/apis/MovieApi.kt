package com.example.base.apis

import com.example.data.dto.MovieDetailDto
import com.example.data.dto.SearchResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("?type=movie")
    suspend fun getSearchResultData(
        @Query("s") searchTitle: String,
        @Query("apikey") apiKey: String,
        @Query("page") pageIndex: Int,

    ): SearchResultsDto

//    @GET("?type=movie")
//    fun getSearchResultData(
//        @Query(value = "s") searchTitle: String, @Query(value = "apiKey") apiKey: String, @Query(
//            value = "page"
//        ) pageIndex: Int
//    ): Call<SearchResultsDto>

    @GET("?plot=full")
    fun getMovieDetailData(@Query(value = "t") title: String, @Query(value = "apiKey") apiKey: String): Call<MovieDetailDto>

}
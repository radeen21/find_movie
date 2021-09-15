package com.example.data.mapper

import com.example.data.dto.SearchResultsDto
import com.example.domain.entities.SearchResults


fun SearchResultsDto.map(): SearchResults {
    return SearchResults(
        this.response,
        this.totalResults,
        this.search?.map { search -> SearchResults.SearchItem(search.type, search.year, search.imdbID,
            search.poster, search.title) },
    )
}
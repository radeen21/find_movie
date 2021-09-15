package com.example.home.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.constant.Constant
import com.example.base.presentation.BaseViewModel
import com.example.base.subscriber.ResultState
import com.example.domain.entities.SearchResults
import com.example.domain.interactor.GetSearchMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel :
    BaseViewModel {

    private var repositoryImpl: GetSearchMovie

    @Inject
    constructor(repositoryImpl: GetSearchMovie) : super(null) {
        this.repositoryImpl = repositoryImpl
    }

    constructor(
        repositoryImpl: GetSearchMovie,
        testScope: CoroutineScope?
    ) : super(testScope) {
        this.repositoryImpl = repositoryImpl
    }

    private var searchTitle: String = ""
    private val mutableRepo = MutableLiveData<ResultState<SearchResults>>()
    val searchData: LiveData<ResultState<SearchResults>> = mutableRepo

    init {
        refreshSearch()
    }

    suspend fun fetchSearch(keyword: String, page:Int) {
        searchTitle = keyword
        repositoryImpl.addParam(GetSearchMovie.Params(keyword, Constant.API_KEY, page))
            .execute(coroutineScope)
            .toResult()
            .run(mutableRepo::postValue)
    }


    fun search(keyword: String, page: Int) {
        GlobalScope.launch { fetchSearch(keyword, page) }
    }

    fun refreshSearch() {
        GlobalScope.launch {
            fetchSearch(searchTitle, 1)
        }
    }
}
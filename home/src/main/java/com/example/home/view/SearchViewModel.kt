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

    private suspend fun fetchSearch() {
        repositoryImpl.addParam(GetSearchMovie.Params("avenger", Constant.API_KEY, 1))
            .execute(coroutineScope)
            .toResult()
            .run(mutableRepo::postValue)
    }

    fun refreshSearch() {
        GlobalScope.launch {
            fetchSearch()
        }
    }
}
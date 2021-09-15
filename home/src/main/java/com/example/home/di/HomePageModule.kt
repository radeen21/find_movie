package com.example.home.di

import com.example.domain.interactor.GetSearchMovie
import com.example.home.view.SearchViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomePageModule {

    @Provides
    @Singleton
    fun provideSearch(interactor: GetSearchMovie): SearchViewModel {
        return SearchViewModel(interactor)
    }

//    @Provides
//    @Singleton
//    fun provideDiscoverVM(interactor: GetDiscoverMoviesByGenre): DiscoverViewModel {
//        return DiscoverViewModel(interactor)
//    }
}
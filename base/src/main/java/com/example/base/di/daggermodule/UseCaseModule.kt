package com.example.base.di.daggermodule

import com.example.domain.interactor.GetSearchMovie
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    fun provideSearchInteractor(
        movieRepository: MovieRepository
    ): GetSearchMovie {
        return GetSearchMovie(movieRepository)
    }

//    @Provides
//    fun provideDiscoverInteractor(
//        movieRepository: MovieRepository
//    ): GetDiscoverMoviesByGenre {
//        return GetDiscoverMoviesByGenre(movieRepository)
//    }
//
//    @Provides
//    fun provideGetReviewInteractor(
//        movieRepository: MovieRepository
//    ): GetReviews {
//        return GetReviews(movieRepository)
//    }
//
//    @Provides
//    fun provideDetailInteractor(
//        movieRepository: MovieRepository
//    ): GetDetailMovie {
//        return GetDetailMovie(movieRepository)
//    }
//
//    @Provides
//    fun provideGetVideosInteractor(
//        movieRepository: MovieRepository
//    ): GetVideos {
//        return GetVideos(movieRepository)
//    }
}
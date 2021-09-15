package com.example.data.internal.di

import com.example.base.apis.MovieApi
import com.example.data.repoimpl.MovieRepoImpl
import com.example.data.webapi.WebApiProvider
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideMovieAPI(webApiProvider: WebApiProvider): MovieApi {
        // Whenever Dagger needs to provide an instance of type LoginRetrofitService,
        // this code (the one inside the @Provides method) is run.
        return webApiProvider.getRetrofit()
            .create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(movieAPI: MovieApi): MovieRepository {
        return MovieRepoImpl(movieAPI)
    }


}
package com.example.movie_find

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ApplicationModule {
    private var app: AndroidApplication? = null

    @Provides
    @Singleton
    fun provideApplicationContext(): Context? {
        return app
    }
}
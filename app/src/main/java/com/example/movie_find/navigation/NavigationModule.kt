package com.example.movie_find.navigation

import com.example.base.navigation.CommonNavigation
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule {

    @Singleton
    @Provides
    fun provideCommonNavigation(): CommonNavigation {
        return CommonNavigationImplementation()
    }
}
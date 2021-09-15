package com.example.movie_find.di

import com.example.base.di.component.BaseComponent
import com.example.base.di.daggermodule.UseCaseModule
import com.example.data.internal.di.DataModule
import com.example.home.di.HomePageModule
import com.example.movie_find.AndroidApplication
import com.example.movie_find.ApplicationModule
import com.example.movie_find.di.ApiModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        DataModule::class,
        UseCaseModule::class,
        HomePageModule::class,
//        DetailModule::class
    ]
)
interface ApplicationComponent : BaseComponent {
    fun inject(androidApplication: AndroidApplication)
}
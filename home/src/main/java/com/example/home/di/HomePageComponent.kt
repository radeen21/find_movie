package com.example.home.di

import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.home.view.MainFragment
import dagger.Component

@Component(
    dependencies = [
        BaseComponent::class,
        BaseNavigationComponent::class
    ]
)
interface HomePageComponent {
    fun inject(mainFragment: MainFragment)
//    fun inject(discoverMovieFragment: DiscoverMovieFragment)
}
package com.example.movie_find

import android.app.Application
import com.example.base.di.ComponentOwner
import com.example.base.di.SubComponentOwner
import com.example.base.di.component.BaseComponent
import com.example.base.di.component.BaseNavigationComponent
import com.example.movie_find.di.DaggerApplicationComponent
import com.example.movie_find.navigation.DaggerNavigationComponent
import java.util.*
import kotlin.reflect.KClass


class AndroidApplication : Application(),
    ComponentOwner<BaseComponent>,
    SubComponentOwner {

    private val subComponent: HashMap<KClass<*>, Any>? = hashMapOf()
    val appComponent = DaggerApplicationComponent.builder().build()
    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
        subComponent?.put(
            BaseNavigationComponent::class,
            DaggerNavigationComponent.builder().build()
        )
    }

    override fun getComponent(): BaseComponent {
        return appComponent
    }

    override fun <SubC> getSubComponent(subClass: Class<SubC>?): SubC? {
        subClass?.let {
            if (BaseNavigationComponent::class.java.isAssignableFrom(it)) {
                return it.cast(subComponent?.get(BaseNavigationComponent::class))
            }
        }
        return null
    }
}
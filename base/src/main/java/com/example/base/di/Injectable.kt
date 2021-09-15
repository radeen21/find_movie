package com.example.base.di

import com.example.base.di.component.BaseComponent

interface Injectable {

    fun injectComponent()

    fun <C : BaseComponent?> getBaseComponent(cClass: Class<C>?): C

    fun <SubC> getAppSubComponent(subClass: Class<SubC>?): SubC
}

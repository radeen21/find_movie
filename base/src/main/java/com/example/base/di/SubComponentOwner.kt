package com.example.base.di


interface SubComponentOwner {
    fun <SubC> getSubComponent(subClass: Class<SubC>?): SubC?
}
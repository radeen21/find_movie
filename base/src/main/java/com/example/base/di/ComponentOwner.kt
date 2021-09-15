package com.example.base.di

interface ComponentOwner<C> {
    fun getComponent(): C
}
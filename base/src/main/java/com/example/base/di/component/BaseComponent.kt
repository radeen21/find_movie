package com.example.base.di.component

import com.example.domain.repository.MovieRepository

interface BaseComponent {
    fun repository(): MovieRepository
}
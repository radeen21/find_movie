package com.example.domain


interface ErrorBundle {
    val exception: Exception?
    val message: String?
}
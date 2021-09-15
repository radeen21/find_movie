package com.example.domain

import io.reactivex.Scheduler

interface PostExecutionThread {
    fun getScheduler(): Scheduler
}
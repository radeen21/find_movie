package com.example.base.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?) =
    coroutineScope
        ?: this.viewModelScope

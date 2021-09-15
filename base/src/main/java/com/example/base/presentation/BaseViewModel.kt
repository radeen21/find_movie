package com.example.base.presentation

import androidx.lifecycle.ViewModel
import com.example.base.subscriber.ResultState
import com.example.base.util.getViewModelScope
import com.example.domain.Either
import kotlinx.coroutines.CoroutineScope

abstract class BaseViewModel(coroutineScope: CoroutineScope?) : ViewModel() {

    val coroutineScope: CoroutineScope = getViewModelScope(coroutineScope)

    protected fun <T> Either<Throwable, T>.toResult() = when (this) {
        is Either.Error -> ResultState.Error<T>(
            this.error
        )
        is Either.Value -> ResultState.Success(
            this.value
        )
    }

    protected fun <T> Either<Throwable, T>.toPaginationResult(
        onResult: (T) -> Unit,
        onError: (Throwable?) -> Unit
    ) = when (this) {
        is Either.Error -> onError(this.error)

        is Either.Value -> onResult(this.value)
    }
}
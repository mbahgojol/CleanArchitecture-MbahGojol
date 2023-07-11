package com.mbahgojol.common.state

sealed interface ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error(val errorMessage: String) : ResultState<Nothing>
    object Loading : ResultState<Nothing>
}
@file:Suppress("unused", "ComposableNaming", "UNUSED_EXPRESSION")

package com.mbahgojol.common.state

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

data class UiState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)

@Composable
fun <T> UiState<T>.addStateListener(
    success: @Composable (T) -> Unit = {},
    error: @Composable (String) -> Unit = {},
    loading: @Composable () -> Unit = {}
) {
    when {
        hasError -> {
            error(errorMessage.toString())
        }

        isLoading -> {
            loading()
        }

        data != null -> {
            success(data)
        }
    }
}

@Composable
fun <T> UiState<T>.addSuccessListener(
    success: @Composable (T) -> Unit = {},
) {
    data?.let { success(it) }
}

@Composable
fun <T> UiState<T>.addLoadingListener(
    loading: @Composable () -> Unit = {}
) {
    if (isLoading) loading
}

@Composable
fun <T> UiState<T>.addErrorListener(
    error: @Composable (String) -> Unit = {},
) {
    if (hasError) error(errorMessage.toString())
}

fun <T> MutableLiveData<UiState<T>>.success(data: T) {
    value = UiState(data = data)
}

fun <T> MutableStateFlow<UiState<T>>.success(data: T) {
    value = UiState(data = data)
}

fun <T> MutableLiveData<UiState<T>>.error(message: String) {
    value = UiState(hasError = true, errorMessage = message)
}

fun <T> MutableStateFlow<UiState<T>>.error(message: String) {
    value = UiState(hasError = true, errorMessage = message)
}

fun <T> MutableStateFlow<UiState<T>>.loading() {
    value = UiState(isLoading = true)
}

fun <T> MutableLiveData<UiState<T>>.loading() {
    value = UiState(isLoading = true)
}

fun <T> MutableLiveData<UiState<T>>.setValue(result: ResultState<T>) {
    when (result) {
        is ResultState.Loading -> {
            loading()
        }

        is ResultState.Success -> {
            success(result.data)
        }

        is ResultState.Error -> {
            error(result.errorMessage)
        }
    }
}

fun <T> MutableStateFlow<UiState<T>>.setValue(result: ResultState<T>) {
    when (result) {
        is ResultState.Loading -> {
            loading()
        }

        is ResultState.Success -> {
            success(result.data)
        }

        is ResultState.Error -> {
            error(result.errorMessage)
        }
    }
}

class MutableUiState<T> {
    operator fun invoke(params: ResultState<T>): MutableStateFlow<UiState<T>> =
        MutableStateFlow<UiState<T>>(UiState()).apply {
            when (params) {
                is ResultState.Loading -> {
                    loading()
                }

                is ResultState.Success -> {
                    success(params.data)
                }

                is ResultState.Error -> {
                    error(params.errorMessage)
                }
            }
        }
}
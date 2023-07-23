@file:Suppress("unused", "ComposableNaming", "UNUSED_EXPRESSION")

package com.mbahgojol.common.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.mbahgojol.common.exception.onException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

data class UiState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null,
)

@Composable
fun <T> UiState<T>.addStateListener(
    success: @Composable (T) -> Unit,
    error: @Composable (String) -> Unit,
    loading: @Composable () -> Unit = {},
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
    success: @Composable (T) -> Unit,
) {
    data?.let { success(it) }
}

@Composable
fun <T> UiState<T>.addLoadingListener(
    loading: @Composable () -> Unit,
) {
    if (isLoading) loading()
}

@Composable
fun <T> UiState<T>.addErrorListener(
    error: @Composable (String) -> Unit,
) {
    if (hasError) error(errorMessage.toString())
}

fun <T> MutableStateFlow<UiState<T>>.success(data: T) {
    value = UiState(data = data)
}

fun <T> MutableStateFlow<UiState<T>>.error(message: String) {
    value = UiState(hasError = true, errorMessage = message)
}

fun <T> MutableStateFlow<UiState<T>>.loading() {
    value = UiState(isLoading = true)
}

fun <T> MutableStateFlow<UiState<T>>.setValue(result: Result<T>) {
    result.onSuccess {
        success(it)
    }.onException {
        error(it.message.toString())
    }
}

fun <T> Flow<UiState<T>>.mergeWithLoading(inProgress: Flow<Boolean>): Flow<UiState<T>> =
    inProgress.flatMapMerge { loading ->
        map {
            it.copy(isLoading = loading)
        }
    }

@Composable
fun <T> Flow<UiState<T>>.collectAsUiState(): State<UiState<T>> {
    return collectAsState(UiState())
}

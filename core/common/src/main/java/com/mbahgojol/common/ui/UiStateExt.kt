@file:Suppress("unused", "ComposableNaming")

package com.mbahgojol.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mbahgojol.common.result.suspendRunCatching
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
    }.onFailure {
        error(it.message.toString())
    }
}

suspend fun <T> MutableStateFlow<UiState<T>>.callRequest(block: suspend () -> T) {
    loading()
    suspendRunCatching {
        block()
    }.onFailure {
        error(it.message.toString())
    }.onSuccess {
        success(it)
    }
}

fun <T> Flow<UiState<T>>.withLoading(inProgress: Flow<Boolean>): Flow<UiState<T>> =
    inProgress.flatMapMerge { loading ->
        map {
            it.copy(isLoading = loading)
        }
    }

@Composable
fun <T> Flow<UiState<T>>.collectAsUiStateWithLifecycle(): State<UiState<T>> {
    return collectAsStateWithLifecycle(UiState())
}



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
    val errorState: ErrorState = ErrorState(),
)

@Composable
fun <T> UiState<T>.addStateListener(
    success: @Composable (T) -> Unit,
    error: @Composable (ErrorState) -> Unit,
    loading: @Composable () -> Unit = {},
) {
    when {
        hasError -> {
            error(errorState)
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
    error: @Composable (ErrorState) -> Unit,
) {
    if (hasError) error(errorState)
}

fun <T> MutableStateFlow<UiState<T>>.success(data: T) {
    value = UiState(data = data)
}

fun <T> MutableStateFlow<UiState<T>>.error(errorState: ErrorState) {
    value = UiState(hasError = true, errorState = errorState)
}

fun <T> MutableStateFlow<UiState<T>>.loading() {
    value = UiState(isLoading = true)
}

fun <T> MutableStateFlow<UiState<T>>.setValue(result: Result<T>) {
    result.onSuccess {
        success(it)
    }.onFailure {
        val errorState = it.toErrorState()
        error(errorState)
    }
}

suspend fun <T> MutableStateFlow<UiState<T>>.callRequest(block: suspend () -> T) {
    loading()
    suspendRunCatching {
        block()
    }.onFailure {
        val errorState = it.toErrorState()
        error(errorState)
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



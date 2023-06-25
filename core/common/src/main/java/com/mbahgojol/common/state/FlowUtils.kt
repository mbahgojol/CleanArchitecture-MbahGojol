@file:Suppress("unused")

package com.mbahgojol.common.state

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import okio.IOException

private const val RETRY_TIME_IN_MILLIS = 15_000L

fun <T : Any> flowState(service: suspend () -> T): Flow<ResultState<T>> = flow {
    emit(ResultState.Loading)
    try {
        emit(ResultState.Success(service.invoke()))
    } catch (e: Exception) {
        emit(ResultState.Error(e.message.toString()))
    }
}.flowIo()

fun <T> Flow<T>.asResultState(): Flow<ResultState<T>> {
    return this.map<T, ResultState<T>> {
        ResultState.Success(it)
    }.onStart { emit(ResultState.Loading) }
        .retryWhen { cause, _ ->
            if (cause is IOException) {
                emit(ResultState.Error(cause.message.toString()))
                delay(RETRY_TIME_IN_MILLIS)
                true
            } else {
                false
            }
        }.catch { emit(ResultState.Error(it.message.toString())) }
        .flowIo()
}

fun flowStateBoolean(service: suspend () -> Any): Flow<Boolean> = flow {
    try {
        service.invoke()
        emit(true)
    } catch (e: Exception) {
        emit(false)
    }
}.flowIo()

fun <T> Flow<T>.flowIo(): Flow<T> = flowOn(Dispatchers.IO)

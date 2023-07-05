@file:Suppress("unused", "SpellCheckingInspection")

package com.mbahgojol.common.interactor

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

abstract class Interactor<in P> {
    operator fun invoke(
        params: P,
        timeoutMs: Long = defaultTimeoutMs,
    ): Flow<Result<Unit>> = flow {
        try {
            withTimeout(timeoutMs) {
                emit(Result.success(doWork(params)))
            }
        } catch (t: TimeoutCancellationException) {
            emit(Result.failure(t))
        }
    }.catch { t -> emit(Result.failure(t)) }

    suspend fun executeSync(params: P) = doWork(params)

    protected abstract suspend fun doWork(params: P)

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}

suspend inline fun Interactor<Unit>.executeSync() = executeSync(Unit)

abstract class ResultInteractor<in P, R> {
    operator fun invoke(params: P): Flow<Result<R>> = flow {
        emit(Result.success(doWork(params)))
    }.catch { t -> emit(Result.failure(t)) }

    protected abstract suspend fun doWork(params: P): R
}

abstract class PagingInteractor<P : PagingInteractor.Parameters<T>, T : Any> :
    SubjectInteractor<P, PagingData<T>>() {
    interface Parameters<T : Any> {
        val pagingConfig: PagingConfig
    }
}

abstract class SuspendingWorkInteractor<P : Any, T> : SubjectInteractor<P, T>() {
    override fun createObservable(params: P): Flow<T> = flow {
        emit(doWork(params))
    }

    abstract suspend fun doWork(params: P): T
}

abstract class SubjectInteractor<P : Any, T> {
    private val paramState = MutableSharedFlow<P>(
        replay = 1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    val flow: Flow<T> = paramState.distinctUntilChanged().flatMapLatest { createObservable(it) }
        .distinctUntilChanged()

    operator fun invoke(params: P) {
        paramState.tryEmit(params)
    }

    protected abstract fun createObservable(params: P): Flow<T>
}

abstract class Interactorp<in P, R> {
    private var count: Int = 0
    private val loadingState = MutableStateFlow(count)

    val inProgress: Flow<Boolean>
        get() = loadingState.map { it > 0 }.distinctUntilChanged()

    private fun addLoader() {
        loadingState.value = count++
    }

    private fun removeLoader() {
        loadingState.value = count--
    }

    suspend operator fun invoke(
        params: P,
        timeout: Duration = DefaultTimeout,
    ): Result<R> {
        return try {
            addLoader()
            runCatching {
                withTimeout(timeout) {
                    doWork(params)
                }
            }
        } finally {
            removeLoader()
        }
    }

    protected abstract suspend fun doWork(params: P): R

    companion object {
        internal val DefaultTimeout = 5.minutes
    }
}

suspend fun <R> Interactorp<Unit, R>.invoke(
    timeout: Duration = Interactorp.DefaultTimeout,
) = invoke(Unit, timeout)

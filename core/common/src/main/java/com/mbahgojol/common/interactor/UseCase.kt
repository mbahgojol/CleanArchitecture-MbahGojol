@file:Suppress("unused")

package com.mbahgojol.common.interactor

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

abstract class UsesCase<in P, out R> {
    operator fun invoke(
        params: P,
        timeoutMs: Long = defaultTimeoutMs,
    ): Flow<Result<R>> = flow {
        try {
            withTimeout(timeoutMs) {
                emit(Result.success(doWork(params)))
            }
        } catch (t: TimeoutCancellationException) {
            emit(Result.failure(t))
        }
    }.catch { t -> emit(Result.failure(t)) }

    suspend fun executeSync(params: P) = doWork(params)

    protected abstract suspend fun doWork(params: P): R

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}
@file:Suppress("unused", "ComposableNaming")

package com.mbahgojol.common.result

import kotlin.coroutines.cancellation.CancellationException
import timber.log.Timber

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (exception: Exception) {
    Timber.tag("suspendRunCatching")
        .i(exception, "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result")
    Result.failure(exception)
}

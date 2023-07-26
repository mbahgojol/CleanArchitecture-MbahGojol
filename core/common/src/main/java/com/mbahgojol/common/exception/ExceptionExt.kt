@file:Suppress("unused")
package com.mbahgojol.common.exception

import com.mbahgojol.common.network.NetworkHelper
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.CancellationException

fun Throwable.toCustomExceptions() = when (this) {
    is ServerResponseException -> HttpErrorInternalServerError()
    is ClientRequestException -> when (response.status.value) {
        400 -> HttpErrorBadRequest()
        401 -> HttpErrorUnauthorized()
        403 -> HttpErrorForbidden()
        404 -> HttpErrorNotFound()
        else -> HttpError()
    }

    is RedirectResponseException -> HttpError()
    else -> GenericError()
}

suspend fun <T> requestData(requestData: suspend () -> T): T {
    return try {
        requestData.invoke()
    } catch (throwable: Throwable) {
        throw throwable.toCustomExceptions()
    }
}

suspend fun <T> NetworkHelper.safeNetworkCall(requestData: suspend () -> T): T {
    return when (isNetworkConnected()) {
        true -> {
            try {
                requestData.invoke()
            } catch (e: Exception) {
                throw e
            }
        }

        false -> throw NoInternetConnection()
    }
}

inline fun Result<*>.onException(
    block: (Throwable) -> Unit,
) {
    val e = exceptionOrNull()
    when {
        e is CancellationException -> throw e
        e != null -> block(e)
    }
}

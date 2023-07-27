@file:Suppress("unused")
package com.mbahgojol.data.utils

import com.mbahgojol.common.exception.GenericError
import com.mbahgojol.common.exception.HttpError
import com.mbahgojol.common.exception.HttpErrorBadRequest
import com.mbahgojol.common.exception.HttpErrorForbidden
import com.mbahgojol.common.exception.HttpErrorInternalServerError
import com.mbahgojol.common.exception.HttpErrorNotFound
import com.mbahgojol.common.exception.HttpErrorUnauthorized
import com.mbahgojol.common.exception.NoInternetConnection
import com.mbahgojol.common.network.NetworkHelper
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

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

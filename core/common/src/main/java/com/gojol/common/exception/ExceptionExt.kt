@file:Suppress("unused")

package com.gojol.common.exception

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

fun Exception.toCustomExceptions() = when (this) {
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

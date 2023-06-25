@file:Suppress("unused")
package com.mbahgojol.common.network

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

fun Exception.toCustomExceptions() = when (this) {
    is ServerResponseException -> Failure.HttpErrorInternalServerError(this)
    is ClientRequestException -> when (response.status.value) {
        400 -> Failure.HttpErrorBadRequest(this)
        401 -> Failure.HttpErrorUnauthorized(this)
        403 -> Failure.HttpErrorForbidden(this)
        404 -> Failure.HttpErrorNotFound(this)
        else -> Failure.HttpError(this)
    }

    is RedirectResponseException -> Failure.HttpError(this)
    else -> Failure.GenericError(this)
}
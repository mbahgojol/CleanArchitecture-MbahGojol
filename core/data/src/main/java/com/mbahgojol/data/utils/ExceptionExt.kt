@file:Suppress("unused")
package com.mbahgojol.data.utils

import com.google.gson.JsonSyntaxException
import com.mbahgojol.common.exceptions.HttpError
import com.mbahgojol.common.exceptions.HttpErrorBadRequest
import com.mbahgojol.common.exceptions.HttpErrorForbidden
import com.mbahgojol.common.exceptions.HttpErrorInternalServerError
import com.mbahgojol.common.exceptions.HttpErrorNotFound
import com.mbahgojol.common.exceptions.HttpErrorRedirect
import com.mbahgojol.common.exceptions.HttpErrorUnauthorized
import com.mbahgojol.common.exceptions.JsonSyntaxError
import com.mbahgojol.common.exceptions.NoInternetConnection
import com.mbahgojol.common.exceptions.toHttpErrorResponse
import com.mbahgojol.common.network.NetworkHelper
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode


suspend fun Throwable.toHttpErrorExceptions() = when (this) {
    is ServerResponseException -> HttpErrorInternalServerError()
    is RedirectResponseException -> HttpErrorRedirect()
    is ClientRequestException -> {
        val httpErrorResponse = response.bodyAsText().toHttpErrorResponse()
        when (response.status) {
            HttpStatusCode.BadRequest -> HttpErrorBadRequest(httpErrorResponse)
            HttpStatusCode.Unauthorized -> HttpErrorUnauthorized(httpErrorResponse)
            HttpStatusCode.Forbidden -> HttpErrorForbidden(httpErrorResponse)
            HttpStatusCode.NotFound -> HttpErrorNotFound(httpErrorResponse)
            else -> HttpError(httpErrorResponse)
        }
    }

    else -> this
}

suspend inline fun <T> safeRequest(
    block: () -> T,
): T = try {
    block.invoke()
} catch (e: JsonSyntaxException) {
    throw JsonSyntaxError()
} catch (e: Throwable) {
    throw e.toHttpErrorExceptions()
}

suspend fun <T> NetworkHelper.safeNetworkCall(requestData: suspend () -> T): T =
    when (isNetworkConnected()) {
        true -> {
            requestData.invoke()
        }

        false -> throw NoInternetConnection()
    }

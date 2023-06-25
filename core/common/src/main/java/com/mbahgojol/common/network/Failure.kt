@file:Suppress("unused")

package com.mbahgojol.common.network

sealed class Failure {
    data class HttpErrorInternalServerError(val e: Exception) : Failure()
    data class HttpErrorBadRequest(val e: Exception) : Failure()
    data class HttpErrorUnauthorized(val e: Exception) : Failure()
    data class HttpErrorForbidden(val e: Exception) : Failure()
    data class HttpErrorNotFound(val e: Exception) : Failure()
    data class HttpError(val e: Exception) : Failure()
    data class GenericError(val e: Exception) : Failure()
}

object NetworkConnection : Failure()
object ServerError : Failure()


abstract class FeatureFailure : Failure()
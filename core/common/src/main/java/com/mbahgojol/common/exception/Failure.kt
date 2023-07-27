@file:Suppress("unused")

package com.mbahgojol.common.exception

import kotlinx.coroutines.CancellationException

class NetworkConnection : Exception()
class HttpErrorInternalServerError : Exception()
class HttpErrorBadRequest : Exception()
class HttpErrorUnauthorized : Exception()
class HttpErrorForbidden : Exception()
class HttpErrorNotFound : Exception()
class HttpError : Exception()
class ServerError : Exception()
class NoInternetConnection : Exception()
class UnexpectedError : Exception()
class GenericError : Exception()
abstract class FeatureFailure : Exception()

inline fun Result<*>.onException(
    block: (Throwable) -> Unit,
) {
    val e = exceptionOrNull()
    when {
        e is CancellationException -> throw e
        e != null -> block(e)
    }
}

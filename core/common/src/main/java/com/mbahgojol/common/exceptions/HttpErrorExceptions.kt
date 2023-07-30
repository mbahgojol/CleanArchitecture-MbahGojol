@file:Suppress("unused")

package com.mbahgojol.common.exceptions

/**
 * Server error exception.
 */
class HttpErrorInternalServerError : HttpError()
class HttpErrorBadRequest(httpErrorResponse: HttpErrorResponse? = null) :
    HttpError(httpErrorResponse)

class HttpErrorUnauthorized(httpErrorResponse: HttpErrorResponse? = null) :
    HttpError(httpErrorResponse)

class HttpErrorForbidden(httpErrorResponse: HttpErrorResponse? = null) :
    HttpError(httpErrorResponse)

class HttpErrorNotFound(httpErrorResponse: HttpErrorResponse? = null) : HttpError(httpErrorResponse)
class HttpErrorRedirect(httpErrorResponse: HttpErrorResponse? = null) : HttpError(httpErrorResponse)
open class HttpError(var httpErrorResponse: HttpErrorResponse? = null) : Exception()

/**
 * Globar error exception.
 */
class NetworkConnection : Exception()
class NoInternetConnection : Exception()
class UnexpectedError : Exception()
class GenericError : Exception()
class FeatureFailure : Exception()
class JsonSyntaxError : Exception()

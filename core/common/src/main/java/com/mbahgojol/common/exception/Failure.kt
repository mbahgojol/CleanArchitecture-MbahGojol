@file:Suppress("unused")

package com.mbahgojol.common.exception

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
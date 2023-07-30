package com.mbahgojol.common.ui

import com.mbahgojol.common.exceptions.HttpError
import com.mbahgojol.common.exceptions.HttpErrorBadRequest
import com.mbahgojol.common.exceptions.HttpErrorInternalServerError
import com.mbahgojol.common.exceptions.JsonSyntaxError

open class ErrorState(
    open var message: String? = null,
    open var code: String? = null,
)

fun Throwable.toErrorState(): ErrorState {
    return when (this) {
        is HttpErrorInternalServerError -> {
            ErrorState(
                message = "Oops! internal server error at our end",
                code = null,
            )
        }

        is HttpErrorBadRequest -> {
            ErrorState(
                message = "Oops! We got a bad gateway. Fixing it. Hold on!",
                code = httpErrorResponse?.code,
            )
        }

        is HttpError -> {
            ErrorState(
                message = httpErrorResponse?.message,
                code = httpErrorResponse?.code,
            )
        }

        is JsonSyntaxError -> {
            ErrorState(
                message = "Oops! Your json is not the same on the server. fix it. Wait!",
                code = null,
            )
        }

        else -> {
            ErrorState(
                message = message,
                code = null,
            )
        }
    }
}

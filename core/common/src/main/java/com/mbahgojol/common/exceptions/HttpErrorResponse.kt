package com.mbahgojol.common.exceptions

import org.json.JSONObject
import timber.log.Timber

data class HttpErrorResponse(
    val code: String,
    val message: String,
    val status: String,
)

fun String.toHttpErrorResponse(): HttpErrorResponse {
    try {
        val jsonObject = JSONObject(this)
        return HttpErrorResponse(
            code = jsonObject.getString("code"),
            message = jsonObject.getString("message"),
            status = jsonObject.getString("status"),
        )
    } catch (e: Exception) {
        Timber.tag("JsonSyntax-HttpErrorResponse").i(e.message.toString())
        throw JsonSyntaxError()
    }
}

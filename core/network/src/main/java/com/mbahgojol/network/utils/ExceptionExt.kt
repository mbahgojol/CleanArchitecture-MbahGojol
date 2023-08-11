@file:Suppress("unused")
package com.mbahgojol.network.utils

import com.google.gson.JsonSyntaxException
import com.mbahgojol.common.exceptions.JsonSyntaxError
import com.mbahgojol.common.exceptions.NoInternetConnection
import com.mbahgojol.common.network.NetworkHelper

inline fun <T> safeRequest(
    block: () -> T,
): T = try {
    block.invoke()
} catch (e: JsonSyntaxException) {
    throw JsonSyntaxError()
} catch (e: Throwable) {
    throw e
}

suspend fun <T> NetworkHelper.safeNetworkCall(requestData: suspend () -> T): T =
    when (isNetworkConnected()) {
        true -> {
            requestData.invoke()
        }

        false -> throw NoInternetConnection()
    }

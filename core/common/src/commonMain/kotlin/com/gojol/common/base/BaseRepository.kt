@file:Suppress("unused")

package com.gojol.common.base

import com.gojol.common.exception.NoInternetConnection
import com.gojol.common.exception.UnexpectedError
import com.gojol.common.exception.toCustomExceptions
import com.gojol.common.network.NetworkHelper
import java.io.IOException

abstract class BaseRepository {

    abstract fun <T> getErrorMessageFromApi(response: T): String

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Result.success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> Result.failure(NoInternetConnection())
                else -> {
                    Result.failure(UnexpectedError())
                }
            }
        }
    }

    suspend fun <T> NetworkHelper.safeNetworkCall(apiCall: suspend () -> T): Result<T> {
        return when (isNetworkConnected()) {
            true -> {
                try {
                    Result.success(apiCall.invoke())
                } catch (e: Exception) {
                    Result.failure(e.toCustomExceptions())
                }
            }

            false -> Result.failure(NoInternetConnection())
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): Result<T> {
        return try {
            Result.success(coroutine.invoke())
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    suspend fun <T> NetworkHelper.proceed(coroutine: suspend () -> T): Result<T> {
        return when (isNetworkConnected()) {
            true -> {
                try {
                    Result.success(coroutine.invoke())
                } catch (exception: Exception) {
                    Result.failure(exception.toCustomExceptions())
                }
            }

            false -> Result.failure(NoInternetConnection())
        }
    }
}

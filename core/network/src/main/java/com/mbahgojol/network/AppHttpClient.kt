@file:Suppress("unused")

package com.mbahgojol.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.mbahgojol.common.exceptions.HttpError
import com.mbahgojol.common.exceptions.HttpErrorBadRequest
import com.mbahgojol.common.exceptions.HttpErrorConnectionTimeout
import com.mbahgojol.common.exceptions.HttpErrorForbidden
import com.mbahgojol.common.exceptions.HttpErrorInternalServerError
import com.mbahgojol.common.exceptions.HttpErrorNotFound
import com.mbahgojol.common.exceptions.HttpErrorRedirect
import com.mbahgojol.common.exceptions.HttpErrorUnauthorized
import com.mbahgojol.common.exceptions.toHttpErrorResponse
import com.mbahgojol.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object AppHttpClient {

    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context,
        authTokenInterceptor: AuthTokenInterceptor
    ): HttpClient = HttpClient(OkHttp) {
        engine {
            clientCacheSize = 10 * 1024 * 1024
            config {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                }

                addInterceptor(
                    ChuckerInterceptor.Builder(context).build(),
                )
                addInterceptor(loggingInterceptor)
                addInterceptor(authTokenInterceptor)

                readTimeout(25, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                writeTimeout(300, TimeUnit.SECONDS)
            }
        }

        expectSuccess = true

        addDefaultResponseValidation()

        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, _ ->
                throw cause.toHttpErrorExceptions()
            }
        }

        install(DefaultRequest) {
            url(BuildConfig.BASE_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}

suspend fun Throwable.toHttpErrorExceptions() = when (this) {
    is ServerResponseException -> HttpErrorInternalServerError()
    is RedirectResponseException -> HttpErrorRedirect()
    is ConnectTimeoutException -> HttpErrorConnectionTimeout()
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

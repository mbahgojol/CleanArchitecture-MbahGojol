@file:Suppress("unused")

package com.mbahgojol.network

import com.mbahgojol.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppHttpClient {

    @Provides
    fun provideHttpClient(
        interceptor: AuthTokenInterceptor
    ) = HttpClient(OkHttp) {
        engine {
            clientCacheSize = 10 * 1024 * 1024
            config {
                val loggingInterceptor = HttpLoggingInterceptor()
                    .apply {
                        if(BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    }
                addInterceptor(loggingInterceptor)
                addInterceptor(interceptor)

                readTimeout(25, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                writeTimeout(300, TimeUnit.SECONDS)
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}
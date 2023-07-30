package com.mbahgojol.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

// Todo handle refresh token here
class AuthTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}

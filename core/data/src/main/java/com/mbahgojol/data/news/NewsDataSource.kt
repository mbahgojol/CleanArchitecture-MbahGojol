package com.mbahgojol.data.news

import com.mbahgojol.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getNews() {
        httpClient.get("/v2/everything?q=Apple&apiKey=${BuildConfig.API_KEY}")
    }
}
package com.mbahgojol.data.network.datasources

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    fun getNews() {

    }
}
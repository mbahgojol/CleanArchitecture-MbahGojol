package com.mbahgojol.data.news

import com.google.gson.Gson
import com.mbahgojol.common.exception.requestData
import com.mbahgojol.core.network.BuildConfig
import com.mbahgojol.model.dtos.ResponseNewsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getNews(): ResponseNewsDto {
        return requestData {
            val response = httpClient.get("/v2/everything?q=Apple&apiKey=${BuildConfig.API_KEY}")
            Gson().fromJson(response.bodyAsText(), ResponseNewsDto::class.java)
        }
    }
}

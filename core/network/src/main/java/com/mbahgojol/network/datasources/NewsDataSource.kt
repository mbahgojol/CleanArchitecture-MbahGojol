package com.mbahgojol.network.datasources

import com.mbahgojol.core.network.BuildConfig
import com.mbahgojol.network.dtos.ArticleDto
import com.mbahgojol.network.dtos.ResponseNewsDto
import com.mbahgojol.network.utils.safeRequest
import com.mbahgojol.network.utils.toDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun getNews(): List<ArticleDto> {
        return safeRequest {
            val response = client.get("/v2/everything?q=Apple&apiKey=${BuildConfig.API_KEY}")
            val responseNewsDto = response.toDto(ResponseNewsDto::class.java)
            responseNewsDto.articles
        }
    }
}

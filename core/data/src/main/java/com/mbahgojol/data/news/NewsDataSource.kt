package com.mbahgojol.data.news

import com.mbahgojol.core.network.BuildConfig
import com.mbahgojol.data.dtos.ArticleDto
import com.mbahgojol.data.dtos.ResponseNewsDto
import com.mbahgojol.data.utils.safeRequest
import com.mbahgojol.data.utils.toDto
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

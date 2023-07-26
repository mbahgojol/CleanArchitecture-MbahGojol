package com.mbahgojol.data.news

import com.mbahgojol.common.exception.HttpErrorInternalServerError
import com.mbahgojol.common.exception.requestData
import com.mbahgojol.common.network.toDto
import com.mbahgojol.core.network.BuildConfig
import com.mbahgojol.model.dtos.ArticleDto
import com.mbahgojol.model.dtos.ResponseNewsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import javax.inject.Inject

class NewsDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getNews(): List<ArticleDto> {
        return requestData {
            val response = httpClient.get("/v2/everything?q=Apple&apiKey=${BuildConfig.API_KEY}")
            val responseNewsDto = response.toDto(ResponseNewsDto::class.java)
            if (responseNewsDto.status == HttpStatusCode.OK.description.lowercase()) {
                responseNewsDto.articles
            } else {
                throw HttpErrorInternalServerError()
            }
        }
    }
}

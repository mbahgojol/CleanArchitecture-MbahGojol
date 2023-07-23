package com.mbahgojol.data.news

import com.mbahgojol.model.dtos.ResponseNewsDto

interface NewsRepository {
    suspend fun getNews(): ResponseNewsDto
}

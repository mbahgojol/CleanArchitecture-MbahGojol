package com.mbahgojol.data.news

import com.mbahgojol.model.dtos.ArticleDto

interface NewsRepository {
    suspend fun getNews(): List<ArticleDto>
}

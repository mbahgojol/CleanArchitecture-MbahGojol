package com.mbahgojol.data.news

import com.mbahgojol.data.dtos.ArticleDto

interface NewsRepository {
    suspend fun getNews(): List<ArticleDto>
}

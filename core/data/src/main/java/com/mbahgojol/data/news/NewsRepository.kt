package com.mbahgojol.data.news

import com.mbahgojol.model.entities.ArticleEntities

interface NewsRepository {
    suspend fun getNews(): List<ArticleEntities>
}

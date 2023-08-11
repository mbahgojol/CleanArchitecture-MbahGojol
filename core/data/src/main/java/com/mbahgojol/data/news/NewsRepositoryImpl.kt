package com.mbahgojol.data.news

import com.mbahgojol.common.network.NetworkHelper
import com.mbahgojol.data.model.toArticleEntities
import com.mbahgojol.model.entities.ArticleEntities
import com.mbahgojol.network.datasources.NewsDataSource

class NewsRepositoryImpl(
    private val networkHelper: NetworkHelper,
    private val newsDataSource: NewsDataSource,
) : NewsRepository {
    override suspend fun getNews(): List<ArticleEntities> {
        return newsDataSource.getNews()
            .map { it.toArticleEntities() }
    }
}

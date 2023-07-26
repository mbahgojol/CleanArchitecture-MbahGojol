package com.mbahgojol.data.news

import com.mbahgojol.common.exception.safeNetworkCall
import com.mbahgojol.common.network.NetworkHelper
import com.mbahgojol.model.dtos.ArticleDto

class NewsRepositoryImpl(
    private val networkHelper: NetworkHelper,
    private val newsDataSource: NewsDataSource,
) : NewsRepository {
    override suspend fun getNews(): List<ArticleDto> {
        return networkHelper.safeNetworkCall {
            newsDataSource.getNews()
        }
    }
}

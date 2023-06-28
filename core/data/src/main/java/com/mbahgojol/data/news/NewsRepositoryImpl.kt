package com.mbahgojol.data.news

import com.mbahgojol.common.base.BaseRepository
import com.mbahgojol.common.network.NetworkHelper
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val newsDataSource: NewsDataSource
) : BaseRepository(),
    NewsRepository {
    override fun <T> getErrorMessageFromApi(response: T): String {
        TODO("Not yet implemented")
    }

    override suspend fun getNews() {
        networkHelper.safeNetworkCall {
            newsDataSource.getNews()
        }
    }
}
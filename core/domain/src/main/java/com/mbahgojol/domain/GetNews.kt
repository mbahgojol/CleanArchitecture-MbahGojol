package com.mbahgojol.domain

import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.coroutine.addDispatcher
import com.mbahgojol.common.interactor.Interactor
import com.mbahgojol.data.news.NewsRepository
import com.mbahgojol.model.dtos.toArticleEntities
import com.mbahgojol.model.entities.ArticleEntities
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatchers: AppDispatchers,
) : Interactor<GetNewsParams, List<ArticleEntities>>() {
    override suspend fun doWork(params: GetNewsParams): List<ArticleEntities> =
        newsRepository.getNews().map {
            it.toArticleEntities()
        }.addDispatcher(dispatchers.io)
}

class GetNewsParams

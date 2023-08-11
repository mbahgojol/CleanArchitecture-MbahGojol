package com.mbahgojol.domain

import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.coroutine.dispatcherOn
import com.mbahgojol.data.news.NewsRepository
import com.mbahgojol.model.entities.ArticleEntities
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatchers: AppDispatchers,
) : Interactor<Unit, List<ArticleEntities>>() {
    override suspend fun doWork(params: Unit): List<ArticleEntities> =
        newsRepository.getNews().dispatcherOn(dispatchers.io)
}

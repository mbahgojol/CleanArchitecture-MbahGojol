package com.mbahgojol.domain

import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.coroutine.addDispatcher
import com.mbahgojol.common.interactor.Interactor
import com.mbahgojol.data.news.NewsRepository
import com.mbahgojol.model.dtos.ResponseNewsDto
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatchers: AppDispatchers,
) : Interactor<GetNewsParams, ResponseNewsDto>() {
    override suspend fun doWork(params: GetNewsParams): ResponseNewsDto =
        newsRepository.getNews().addDispatcher(dispatchers.io)
}

class GetNewsParams

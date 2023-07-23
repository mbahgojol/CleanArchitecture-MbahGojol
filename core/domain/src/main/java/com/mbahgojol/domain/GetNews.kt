package com.mbahgojol.domain

import com.mbahgojol.model.dtos.ResponseNewsDto
import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.interactor.Interactor
import com.mbahgojol.data.news.NewsRepository
import javax.inject.Inject
import kotlinx.coroutines.withContext

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
    private val dispatchers: AppDispatchers,
) : Interactor<GetNewsParams, ResponseNewsDto>() {
    override suspend fun doWork(params: GetNewsParams): ResponseNewsDto {
        return withContext(dispatchers.io) {
            newsRepository.getNews()
        }
    }
}

class GetNewsParams

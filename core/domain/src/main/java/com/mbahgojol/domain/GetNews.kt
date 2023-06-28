package com.mbahgojol.domain

import com.mbahgojol.common.interactor.Interactor
import com.mbahgojol.data.news.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository,
) : Interactor<GetNewsParams>() {
    override suspend fun doWork(params: GetNewsParams) {
        return newsRepository.getNews()
    }
}

class GetNewsParams
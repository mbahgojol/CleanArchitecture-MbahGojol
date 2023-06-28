package com.mbahgojol.domain

import com.mbahgojol.common.interactor.Interactor
import com.mbahgojol.data.news.NewsRepositoryImpl
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepositoryImpl,
) : Interactor<GetNewsParams>() {
    override suspend fun doWork(params: GetNewsParams) {
        return newsRepository.getNews()
    }
}

class GetNewsParams
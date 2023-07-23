package com.mbahgojol.data.di

import com.mbahgojol.common.network.NetworkHelper
import com.mbahgojol.data.news.NewsDataSource
import com.mbahgojol.data.news.NewsRepository
import com.mbahgojol.data.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideNewsRepository(
        networkHelper: NetworkHelper,
        newsDataSource: NewsDataSource,
    ): NewsRepository = NewsRepositoryImpl(networkHelper, newsDataSource)
}

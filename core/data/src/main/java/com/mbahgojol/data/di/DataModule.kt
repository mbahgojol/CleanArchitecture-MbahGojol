package com.mbahgojol.data.di

import com.mbahgojol.data.news.NewsRepository
import com.mbahgojol.data.news.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNewsRepository(
        newsRepositoryImpl: NewsRepositoryImpl,
    ): NewsRepository
}

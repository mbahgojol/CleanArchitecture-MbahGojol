package com.mbahgojol.common.di

import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.coroutine.AppDispatchersImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Provides
    fun provideAppDispatchers(): AppDispatchers = AppDispatchersImpl()
}

package com.mbahgojol.common.di

import com.mbahgojol.common.coroutine.AppDispatchers
import com.mbahgojol.common.coroutine.AppDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {
    @Binds
    fun bindsAppDispatchers(
        appDispatchersImpl: AppDispatchersImpl,
    ): AppDispatchers
}

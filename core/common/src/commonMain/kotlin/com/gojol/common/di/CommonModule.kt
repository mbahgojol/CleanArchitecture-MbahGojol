package com.gojol.common.di

import com.gojol.common.coroutine.AppDispatchers
import com.gojol.common.coroutine.AppDispatchersImpl
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

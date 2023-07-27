@file:Suppress("unused")

package com.mbahgojol.common.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext

interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class AppDispatchersImpl : AppDispatchers {
    override val io: CoroutineDispatcher = IO
    override val main: CoroutineDispatcher = Main
    override val default: CoroutineDispatcher = Default
}


suspend fun <T> T.dispatcherOn(coroutineDispatcher: CoroutineDispatcher): T {
    return withContext(coroutineDispatcher) {
        this@dispatcherOn
    }
}

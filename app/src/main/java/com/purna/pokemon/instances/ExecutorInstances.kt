package com.purna.pokemon.instances

import com.purna.base.Dispatchers
import com.purna.base.Executors
import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.pokemon.async.AppExecutor
import com.purna.pokemon.async.CommonExecutor
import com.purna.pokemon.async.IOExecutor
import com.purna.pokemon.async.MainThreadExecutor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asCoroutineDispatcher

/**
 * Genrators for [AppExecutor] or [Executors] or [Dispatchers] instances
 */

object ExecutorInstances {
    private val commonExecutorGenerator: BaseGenerator<AppExecutor> = single { CommonExecutor() }

    private val ioExecutorGenerator: BaseGenerator<AppExecutor> = single { IOExecutor() }

    private val mainExecutorGenerator: BaseGenerator<AppExecutor> = single { MainThreadExecutor() }

    private val appExecutorsProvider: BaseGenerator<Executors> = single {
        Executors(
            mainExecutor = mainExecutorGenerator.getInstance().executor,
            ioExecutor = ioExecutorGenerator.getInstance().executor,
            commonExecutor = commonExecutorGenerator.getInstance().executor
        )
    }

    private val appDispatchersProvider: BaseGenerator<Dispatchers> = single {
        Dispatchers(
            mainDispatcher = appExecutorsProvider.getInstance().mainExecutor.asCoroutineDispatcher(),
            ioDispatcher = appExecutorsProvider.getInstance().ioExecutor.asCoroutineDispatcher(),
            commonDispatcher = appExecutorsProvider.getInstance().commonExecutor.asCoroutineDispatcher()
        )
    }

    val appExecutors: Executors
        get() = appExecutorsProvider.getInstance()

    val appDispatchers: Dispatchers
        get() = appDispatchersProvider.getInstance()

    val mainDispatcher: CoroutineDispatcher
        get() = appDispatchers.mainDispatcher

    val ioDispatcher: CoroutineDispatcher
        get() = appDispatchers.ioDispatcher

    val commonDispatcher: CoroutineDispatcher
        get() = appDispatchers.commonDispatcher
}
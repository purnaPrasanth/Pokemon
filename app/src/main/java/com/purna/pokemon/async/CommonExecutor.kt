package com.purna.pokemon.async

import android.os.AsyncTask
import java.util.concurrent.Executor

/**
 * Executor for Common Computational tasks
 */

class CommonExecutor : AppExecutor {
    override val executor: Executor
        get() = AsyncTask.THREAD_POOL_EXECUTOR
}
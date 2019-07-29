package com.purna.pokemon.async

import java.util.concurrent.Executor

/**
 * Base Executor
 */

interface AppExecutor {
    val executor: Executor
}
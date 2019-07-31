package com.purna.pokemon.data

/**
 * Base class for NetworkResult of a Network Call
 * @param T the data that this is holding
 */
sealed class NetworkResult<T> {
    abstract fun get(): T?
}

/**
 * Success implementation of Network Result [NetworkResult]
 * @param T data that this Success result holds
 */
data class Success<T>(val data: T) : NetworkResult<T>() {
    override fun get() = data
}

/**
 * Error implementation of Network Result [NetworkResult]
 * @param exception Error Details for Failure
 */
data class Error<T>(val exception: Exception) : NetworkResult<T>() {
    override fun get() = null
}
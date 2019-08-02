package com.purna.pokemon.http

import kotlinx.coroutines.CoroutineDispatcher
import java.util.concurrent.TimeUnit

/**
 * Http Client to make network requests, also a factory for [Call] instances by implementing [Call.Factory]
 *
 * @param dispatcher [CoroutineDispatcher] to run requests on
 */

class HttpClient(
    val dispatcher: CoroutineDispatcher,
    private val connectTimeOut: Long,
    private val readTimeOut: Long,
    private val timeUnit: TimeUnit
) : Call.Factory {
    override fun newCall(request: Request): Call = SimpleCall(this, request)

    fun connectTimeOutInMillis() = timeUnit.toMillis(connectTimeOut)

    fun readTimeOutInMillis() = timeUnit.toMillis(readTimeOut)
}
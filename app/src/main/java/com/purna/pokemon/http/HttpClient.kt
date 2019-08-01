package com.purna.pokemon.http

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Http Client to make network requests, also a factory for [Call] instances by implementing [Call.Factory]
 *
 * @param dispatcher [CoroutineDispatcher] to run requests on
 */

class HttpClient(
    val dispatcher: CoroutineDispatcher
) : Call.Factory {
    override fun newCall(request: Request): Call = SimpleCall(this, request)
}
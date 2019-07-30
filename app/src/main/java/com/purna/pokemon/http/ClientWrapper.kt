package com.purna.pokemon.http

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/
class ClientWrapper(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun makeRequest(url: String): String = coroutineScope {
        withContext(dispatcher) {
            val request = Request("GET", HttpUrl.parseUrl(url))
            val response = client.newCall(request).execute()
            response.response.string()
        }
    }
}
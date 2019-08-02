package com.purna.pokemon.http

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * A Simple Implementation of [Call]
 */

class SimpleCall(
    private val client: HttpClient,
    private val request: Request
) : Call {

    override fun request() = request

    override suspend fun execute(): Response = coroutineScope {
        withContext(client.dispatcher) {
            val con = URL(request.httpUrl.toString()).openConnection() as HttpURLConnection

            con.requestMethod = request.method

            val responseCode = con.responseCode

            Response(request, SimpleResponseBody(InputStreamReader(con.inputStream)), responseCode)
        }
    }
}
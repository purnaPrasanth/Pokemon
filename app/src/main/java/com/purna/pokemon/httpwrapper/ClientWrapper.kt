package com.purna.pokemon.httpwrapper

import com.purna.pokemon.http.HttpClient
import com.purna.pokemon.http.HttpUrl
import com.purna.pokemon.http.Request
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer

/**
 * A Simple Wrapper class for [HttpClient]
 */

class ClientWrapper(
    private val client: HttpClient,
    private val converterFactory: Converter.Factory = KotlinxSerializationFactory()
) {
    suspend fun <T> getResource(serializer: KSerializer<T>, url: String): T = coroutineScope {
        withContext(client.dispatcher) {
            val httpUrl = HttpUrl.parseUrl(url)
            val request = Request(method = "GET", httpUrl = httpUrl)
            val call = client.newCall(request)
            val response = call.execute()
            val converter = converterFactory.converterForResponseBody(serializer)
            val parsedResponse = converter.convert(response.body)
            response.close()
            parsedResponse
        }
    }
}
package com.purna.pokemon.httpwrapper

import com.purna.pokemon.http.HttpClient
import com.purna.pokemon.http.HttpUrl
import com.purna.pokemon.http.Request
import com.purna.pokemon.httpwrapper.exception.DefaultExceptionMapper
import com.purna.pokemon.httpwrapper.exception.ICodeToExceptionMapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownServiceException

/**
 * A Simple Wrapper class for [HttpClient]
 */

class ClientWrapper(
    private val client: HttpClient,
    private val converterFactory: Converter.Factory = KotlinxSerializationFactory(),
    private val exceptionMapper: ICodeToExceptionMapper = DefaultExceptionMapper()
) {
    suspend fun <T> getResource(serializer: KSerializer<T>, url: String): T = coroutineScope {
        withContext(client.dispatcher) {
            val httpUrl = HttpUrl.parseUrl(url)
            val request = Request(method = "GET", httpUrl = httpUrl)
            val call = client.newCall(request)
            val response = call.execute()
            val exception = exceptionMapper.mapCodeToException(response.responseCode)
            if (exception != null) throw exception.copy(url = url)
            try {
                val converter = converterFactory.converterForResponseBody(serializer)
                val parsedResponse = converter.convert(response.body)
                parsedResponse
            } catch (exception: Exception) {
                throw when (exception) {
                    is SocketTimeoutException -> {
                        SocketTimeoutException("Socket Time Out While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is UnknownServiceException -> {
                        UnknownServiceException("UnKnown Service While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is IOException -> {
                        IOException("IO Exception While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    else -> {
                        exception
                    }
                }
            } finally {
                response.close()
            }
        }
    }
}
package com.purna.pokemon.http

import com.purna.pokemon.http.connection.Connection
import com.purna.pokemon.http.exception.DefaultExceptionMapper
import com.purna.pokemon.http.exception.ICodeToExceptionMapper
import com.purna.pokemon.http.parse.BaseParser
import com.purna.pokemon.http.parse.DefaultParser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

class HttpClient(
    val parser: BaseParser = DefaultParser(),
    val dispatcher: CoroutineDispatcher,
    val readTimeOut: Long,
    val connectTimeout: Long,
    val timeUnit: TimeUnit,
    val exceptionMapper: ICodeToExceptionMapper = DefaultExceptionMapper()
) {
    suspend fun <T> makeRequest(connection: Connection, responseClass: Class<T>): T = coroutineScope {
        withContext(dispatcher) {
            connection.setReadTimeOut(readTimeOut, timeUnit)
            connection.setConnectTimeOut(connectTimeout, timeUnit)
            val responseCode = connection.getResponseCode()

            val exception = exceptionMapper.mapCodeToException(responseCode)

            if (exception == null) {
                try {
                    val response = StringBuffer()
                    val data = BufferedReader(InputStreamReader(connection.getInputStream()))

                    var inputLine = data.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = data.readLine()
                    }

                    data.close()

                    //print result
                    parser.parse(response.toString(), responseClass)
                } catch (exception: Exception) {
                    exception.printStackTrace()
                    throw exception
                }
            } else {
                throw exception
            }
        }
    }
}
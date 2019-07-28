package com.purna.httpclient.connection

import com.purna.httpclient.exception.ICodeToExceptionMapper
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownServiceException

/**
 * Created by Purna on 2019-06-22 as a part of Image-Viewers
 **/

/**
 * Connection Class for HttpConnections
 * @param readTimeOut set the time out for Reading content
 * @param connectTimeOut set the time out for making a Http Connecting
 * @param exceptionMapper [ICodeToExceptionMapper] for Mapping Response Codes to HttpException
 */

class HttpConnection(
    private val readTimeOut: Int,
    private val connectTimeOut: Int,
    private val exceptionMapper: ICodeToExceptionMapper
) {

    /**
     * make a GET request
     * @param url [URL] to be called
     * @return The resulting [String] from a Network Call
     */
    suspend fun get(url: URL): String {
        val con = url.openConnection() as HttpURLConnection

        con.requestMethod = "GET"

        con.connectTimeout = connectTimeOut
        con.readTimeout = readTimeOut

        val responseCode = con.responseCode

        val exception = exceptionMapper.mapCodeToException(responseCode)

        if (exception == null) {
            val response = StringBuffer()
            try {
                val data = BufferedReader(InputStreamReader(con.inputStream))

                var inputLine = data.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = data.readLine()
                }

                data.close()
            } catch (exception: Exception) {
                when (exception) {
                    is SocketTimeoutException -> {
                        throw SocketTimeoutException("Socket Time Out While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is UnknownServiceException -> {
                        throw UnknownServiceException("UnKnown Service While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is IOException -> {
                        throw IOException("IO Exception While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    else -> {
                        throw exception
                    }
                }
            }

            //print result
            return response.toString()
        } else {
            throw exception.copy(url = url.toString())
        }
    }

    /**
     * make a GET request
     * @param url [URL] to be called
     * @return The resulting [InputStream] from a Network Call
     */
    suspend fun getStream(url: URL): InputStream {
        val con = url.openConnection() as HttpURLConnection

        con.requestMethod = "GET"

        con.connectTimeout = connectTimeOut
        con.readTimeout = readTimeOut

        val responseCode = con.responseCode

        val exception = exceptionMapper.mapCodeToException(responseCode)

        if (exception == null) {

            try {
                // optional default is GET

                return con.inputStream
            } catch (exception: Exception) {
                when (exception) {
                    is IOException -> {
                        throw IOException("IO Exception While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is UnknownServiceException -> {
                        throw UnknownServiceException("UnKnown Service While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    is SocketTimeoutException -> {
                        throw SocketTimeoutException("Socket Time Out While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                    else -> {
                        throw UnknownError("UnKnown Error While trying to connect $url").apply {
                            stackTrace = exception.stackTrace
                        }
                    }
                }
            }
        } else {
            throw exception.copy(url = url.toString())
        }
    }
}
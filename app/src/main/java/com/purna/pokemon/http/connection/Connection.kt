package com.purna.pokemon.http.connection

import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

sealed class Connection(val url: String) {

    val connection: HttpURLConnection by lazy { createConnection() }

    protected abstract fun createConnection(): HttpURLConnection

    fun getInputStream() = connection.inputStream

    fun getResponseCode() = connection.responseCode

    fun setReadTimeOut(time: Long, timeUnit: TimeUnit) {
        connection.readTimeout = timeUnit.toMillis(time).toInt()
    }

    fun setConnectTimeOut(time: Long, timeUnit: TimeUnit) {
        connection.connectTimeout = timeUnit.toMillis(time).toInt()
    }
}

class GetConnection(url: String) : Connection(url) {
    override fun createConnection(): HttpURLConnection {
        val url = URL(url)
        // todo; verify if url points to http resource
        return (url.openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
        }
    }
}
package com.purna.pokemon.http

import com.purna.pokemon.http.util.*
import java.net.MalformedURLException

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/
class HttpUrl(
    private val scheme: String,
    private val host: String,
    private val pathSegments: List<String>,
    private val queriesAndNames: List<String>? = null
) {

    override fun toString(): String {
        return "$scheme://$host/${pathSegments.joinToString("/")}?${namesAndValuesToQueryString(queriesAndNames)}"
    }

    companion object {
        @Throws(MalformedURLException::class)
        fun parseUrl(url: String): HttpUrl {
            var currPos = 0
            val schemeOffset = schemeDelimiter(url)
            currPos += (schemeOffset + 1)
            if (schemeOffset == -1) throw MalformedURLException("Unsupported Protocol $url. Should be http or https")
            val scheme = url.substring(0, schemeOffset)
            val slashCount = slashCount(url, currPos, url.length)
            currPos += slashCount
            val nextSlash = delimiterOffset(url, currPos, url.length, "/")
            val hostName = url.substring(currPos, nextSlash)
            currPos = nextSlash + 1

            val pathEnd = delimiterOffset(url, currPos, url.length, "?")

            val pathSegments: List<String> = if (currPos < url.length) url.substring(currPos, pathEnd).split("/")
            else emptyList()
            currPos = pathEnd + 1
            val queries = if (currPos < url.length) decodeQueryParams(url, currPos, url.length)
            else null

            return HttpUrl(
                scheme, hostName, pathSegments, queries
            )
        }
    }
}
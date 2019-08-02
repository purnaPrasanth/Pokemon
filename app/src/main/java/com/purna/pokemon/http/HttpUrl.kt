package com.purna.pokemon.http

import com.purna.pokemon.http.util.*
import java.net.MalformedURLException

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * A uniform resources locator with Scheme either as http or https
 *
 */

class HttpUrl(
    val scheme: String,
    val host: String,
    val pathSegments: List<String>? = null,
    val queriesAndNames: List<String>? = null
) {

    override fun toString(): String {
        val urlBuilder = StringBuilder("$scheme://$host")
        val path = pathSegments?.joinToString("/")
        if (path != null && path.isNotEmpty()) urlBuilder.append("/$path")
        val query = namesAndValuesToQueryString(queriesAndNames)
        if (query.isNotEmpty()) urlBuilder.append("?$query")
        return urlBuilder.toString()
    }

    companion object {
        /**
         * Parse url into [HttpUrl]
         * @param url Url string to be parsed
         *
         * @return HttpUrl parsed from given [url]
         * @throws MalformedURLException If the scheme is not http or https
         */
        @Throws(MalformedURLException::class)
        fun parseUrl(url: String): HttpUrl {
            var currPos = 0
            val schemeOffset = schemeDelimiter(url)
            val scheme = url.substring(0, schemeOffset)
            currPos += (schemeOffset + 1)
            if (scheme != "http" && scheme != "https") throw MalformedURLException("Unsupported Protocol $url. Should be http or https")
            val slashCount = slashCount(url, currPos, url.length)
            currPos += slashCount
            if (currPos >= url.length) throw MalformedURLException("No Host found in $url")
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
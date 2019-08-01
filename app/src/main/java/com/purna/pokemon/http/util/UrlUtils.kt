package com.purna.pokemon.http.util

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * Delimitter for a scheme in an url
 *
 * @param url
 * @return delimitter count for scheme of the url
 */

fun schemeDelimiter(url: String): Int = when {
    url.startsWith("https") -> 5
    url.startsWith("http") -> 4
    else -> -1
}

/** Returns the number of '/' and '\' slashes in `input`, starting at `pos`.  */
fun slashCount(input: String, pos: Int, limit: Int): Int {
    var start = pos
    var slashCount = 0
    while (start < limit) {
        val c = input[start]
        if (c == '\\' || c == '/') {
            slashCount++
            start++
        } else {
            break
        }
    }
    return slashCount
}

/**
 * @param input Input String
 * @param pos Starting point to start the search
 * @param limit End point to limit the search
 * @param delimiters
 *
 * @return Returns the index of the first character in {@code input} that contains a character in {@code
 * delimiters}. Returns limit if there is no such character.
 */
fun delimiterOffset(input: String, pos: Int, limit: Int, delimiters: String): Int {
    for (i in pos until limit) {
        if (delimiters.indexOf(input[i]) != -1) return i
    }
    return limit
}

/**
 * @param input Input String
 * @param start Starting point to start the search
 * @param limit End point to limit the search
 *
 * @return empty list of no query params else returns the list of query chunks. key -> value -> key -> value
 */
fun decodeQueryParams(input: String, start: Int, limit: Int): List<String> {
    return input.substring(start, limit).split("&").fold(mutableListOf()) { currList, query ->
        currList.addAll(query.split("="))
        currList
    }
}

/**
 * Constructs Query from names and values list
 * @param namesAndValues list of names and values for query
 *
 * @return "" if names and values in empty else query string
 */
fun namesAndValuesToQueryString(namesAndValues: List<String>?): String {
    if (namesAndValues == null) return ""
    var i = 0
    val builder = StringBuilder()
    val size = namesAndValues.size
    while (i < size) {
        val name = namesAndValues[i]
        val value = namesAndValues[i + 1]
        if (i > 0) builder.append('&')
        builder.append(name)
        if (value != null) {
            builder.append('=')
            builder.append(value)
        }
        i += 2
    }

    return builder.toString()
}
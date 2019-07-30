package com.purna.pokemon.http.util

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

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

fun delimiterOffset(input: String, pos: Int, limit: Int, delimiters: String): Int {
    for (i in pos until limit) {
        if (delimiters.indexOf(input[i]) != -1) return i
    }
    return limit
}

fun decodeQueryParams(input: String, start: Int, limit: Int): List<String> {
    return input.substring(start, limit).split("&").fold(mutableListOf()) { currList, query ->
        currList.addAll(query.split("="))
        currList
    }
}

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
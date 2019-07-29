package com.purna.pokemon.http.parse

interface BaseParser {
    fun <T> parse(jsonString: String, cls: Class<T>): T
}
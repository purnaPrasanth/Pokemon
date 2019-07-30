package com.purna.pokemon.http

import java.io.Reader

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/
class SimpleResponseBody(reader: Reader) : ResponseBody(reader) {

    override fun string(): String {
        val strBuilder = StringBuilder()
        val bufferedReader = reader.buffered()

        var line = bufferedReader.forEachLine {
            strBuilder.append(it)
        }

        return strBuilder.toString()
    }
}
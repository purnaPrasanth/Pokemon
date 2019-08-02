package com.purna.pokemon.http

import java.io.Reader

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/

/**
 * A Simple Implementation of [ResponseBody]
 */

class SimpleResponseBody(reader: Reader?) : ResponseBody(reader) {

    override fun string(): String {
        if (reader == null) throw IllegalStateException("Invalid ResponseBody")
        val strBuilder = StringBuilder()
        val bufferedReader = reader.buffered()

        bufferedReader.forEachLine {
            strBuilder.append(it)
        }

        return strBuilder.toString()
    }
}
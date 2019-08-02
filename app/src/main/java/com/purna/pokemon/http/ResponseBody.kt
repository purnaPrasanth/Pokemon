package com.purna.pokemon.http

import java.io.Closeable
import java.io.Reader

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * A one-shot stream from the origin server to the client application with the raw bytes of the
 * response body. Each response body is supported by an active connection to the webserver.
 *
 * This must be closed after use
 */

abstract class ResponseBody(protected val reader: Reader?) : Closeable {
    abstract fun string(): String

    override fun close() {
        reader?.close()
    }
}
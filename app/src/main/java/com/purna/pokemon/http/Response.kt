package com.purna.pokemon.http

import java.io.Closeable

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * An HTTP response.The response body is a one-shot
 * value that may be consumed only once and then closed.
 *
 * <p>This class implements {@link Closeable}. Closing it simply closes its response body. See
 * [ResponseBody] for an explanation and examples.
 *
 * This must be closed after use
 */

class Response(
    private val request: Request,
    val body: ResponseBody,
    val responseCode: Int
) : Closeable {
    override fun close() {
        body.close()
    }

}
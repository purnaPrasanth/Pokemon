package com.purna.pokemon.http

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * An HTTP request. Instances of this class are immutable
 */

class Request(
    val method: String,
    val httpUrl: HttpUrl
)
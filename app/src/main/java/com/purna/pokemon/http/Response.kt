package com.purna.pokemon.http

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/
class Response(
    private val request: Request,
    val body: ResponseBody
)
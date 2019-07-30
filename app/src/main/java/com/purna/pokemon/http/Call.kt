package com.purna.pokemon.http

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/
interface Call {
    fun request(): Request

    suspend fun execute(): Response

    interface Factory {
        fun newCall(request: Request): Call
    }
}
package com.purna.pokemon.http

/**
 * Created by Purna on 2019-07-30 as a part of Pokemon
 **/

/**
 * A call is a request that has been prepared for execution. A call can be canceled. As this object
 * represents a single request/response pair (stream), it cannot be executed twice.
 */
interface Call {
    /**
     * Returns the original request that initiated this call.
     **/
    fun request(): Request

    /**
     * Invokes the Request Immediately in a suspending manner
     */
    suspend fun execute(): Response

    /**
     * Factory Interface for [Call] Instances
     */
    interface Factory {
        fun newCall(request: Request): Call
    }
}
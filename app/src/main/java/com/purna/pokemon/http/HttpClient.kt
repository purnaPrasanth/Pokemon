package com.purna.pokemon.http

import kotlinx.coroutines.CoroutineDispatcher

class HttpClient(
    val dispatcher: CoroutineDispatcher
) : Call.Factory {
    override fun newCall(request: Request): Call = DefaultCall(this, request)



}
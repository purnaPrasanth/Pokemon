package com.purna.pokemon.http.request

import com.purna.pokemon.http.HttpClient

class Request<RESPONSE>(
    val httpClient: HttpClient,
    val url: String,
    val responseClass: Class<RESPONSE>
) {
}
package com.purna.pokemon.httpwrapper

import com.purna.pokemon.http.ResponseBody
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

interface Converter<FROM, TO> {
    fun convert(from: FROM): TO

    interface Factory {
        fun <T> converterForResponseBody(serializer: KSerializer<T>): Converter<ResponseBody, T>
    }
}

class KotlinxSerializationFactory : Converter.Factory {
    private val json = Json(JsonConfiguration.Stable.copy(strictMode = false))

    override fun <T> converterForResponseBody(serializer: KSerializer<T>): Converter<ResponseBody, T> {
        return object : Converter<ResponseBody, T> {
            override fun convert(from: ResponseBody): T {
                return json.parse(serializer, from.string())
            }
        }
    }

}
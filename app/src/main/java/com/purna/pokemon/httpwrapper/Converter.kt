package com.purna.pokemon.httpwrapper

import com.purna.pokemon.http.ResponseBody
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * An Interface to convert one type of object to other
 */

interface Converter<FROM, TO> {
    fun convert(from: FROM): TO

    /**
     * A Factory Interface for creating converters to convert [ResponseBody] to other types
     */
    interface Factory {
        /**
         * to get a convert to convert [ResponseBody] to any other type
         * @param T the type to be parsed to
         * @param serializer Serialization/DeSerialization strategy to be used to unparse
         */
        fun <T> converterForResponseBody(serializer: KSerializer<T>): Converter<ResponseBody, T>
    }
}

/**
 * A Simple Implementation of [Converter.Factory]
 */

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
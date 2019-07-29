package com.purna.pokemon.http.parse

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.serializerByTypeToken

class DefaultParser : BaseParser {
    private val json = Json(JsonConfiguration.Stable.copy(strictMode = false))

    override fun <T> parse(jsonString: String, cls: Class<T>): T {
        val serializer = serializerByTypeToken(cls) as KSerializer<T>
        return json.parse(serializer, jsonString)
    }
}
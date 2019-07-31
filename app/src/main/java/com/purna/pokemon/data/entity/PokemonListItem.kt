package com.purna.pokemon.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class PokemonListItem(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
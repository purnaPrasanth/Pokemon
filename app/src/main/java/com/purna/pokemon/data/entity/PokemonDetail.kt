package com.purna.pokemon.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
    @SerialName("id") val id: Long,
    @SerialName("species") val species: Species
)

@Serializable
data class Species(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
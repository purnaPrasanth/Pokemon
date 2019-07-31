package com.purna.pokemon.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDetail(
    @SerialName("evolution_chain") val evolutionChain: EvolutionChain
)

@Serializable
data class EvolutionChain(
    @SerialName("url") val url: String
)
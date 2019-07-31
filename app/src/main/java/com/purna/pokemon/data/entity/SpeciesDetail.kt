package com.purna.pokemon.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDetail(
    @SerialName("evolution_chain") val evolutionChain: EvolutionChainUrl
)

@Serializable
data class EvolutionChainUrl(
    @SerialName("url") val url: String
)
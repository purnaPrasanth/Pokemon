package com.purna.pokemon.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/

@Serializable
data class EvolutionChain(
    @SerialName("chain") val chain: EvolutionChainItem,
    @SerialName("id") val id: Int
)

@Serializable
data class EvolutionChainItem(
    @SerialName("evolves_to") val evolvesTo: List<EvolutionChainItem>,
    @SerialName("species") val species: EvolutionItemSpecies
)

@Serializable
data class EvolutionItemSpecies(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
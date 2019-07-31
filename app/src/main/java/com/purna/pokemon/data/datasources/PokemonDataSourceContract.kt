package com.purna.pokemon.data.datasources

import com.purna.pokemon.data.NetworkResult
import com.purna.pokemon.data.entity.*

interface PokemonDataSourceContract {
    suspend fun getListOfPokemon(): NetworkResult<PaginatedData<PokemonListItem>>

    suspend fun getListOfPokemon(url: String): NetworkResult<PaginatedData<PokemonListItem>>

    suspend fun getPokemonDetail(url: String): NetworkResult<PokemonDetail>

    suspend fun getSpeciesDetail(url: String): NetworkResult<SpeciesDetail>

    suspend fun getEvolutionChain(url: String): NetworkResult<EvolutionChain>

}
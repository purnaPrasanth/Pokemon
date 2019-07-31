package com.purna.pokemon.data.datasources

import com.purna.pokemon.data.NetworkResult
import com.purna.pokemon.data.entity.PaginatedData
import com.purna.pokemon.data.entity.PokemonDetail
import com.purna.pokemon.data.entity.PokemonListItem
import com.purna.pokemon.data.entity.SpeciesDetail

interface PokemonDataSourceContract {
    suspend fun getListOfPokemon(): NetworkResult<PaginatedData<PokemonListItem>>

    suspend fun getListOfPokemon(url: String): NetworkResult<PaginatedData<PokemonListItem>>

    suspend fun getPokemonDetail(url: String): NetworkResult<PokemonDetail>

    suspend fun getSpeciesDetail(url: String): NetworkResult<SpeciesDetail>

}
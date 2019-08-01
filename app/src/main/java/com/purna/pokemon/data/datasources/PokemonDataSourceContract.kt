package com.purna.pokemon.data.datasources

import com.purna.pokemon.data.NetworkResult
import com.purna.pokemon.data.entity.*

/**
 * Contract to be implemented by different source of Pokemon Data
 * known Subclasses [PokemonDataSourceImpl]
 */

interface PokemonDataSourceContract {

    /**
     * To get an Initial List of Pokemon or First Page of Pokemon Data
     *
     * @return Result of the Network Call [NetworkResult]
     */
    suspend fun getListOfPokemon(): NetworkResult<PaginatedData<PokemonListItem>>

    /**
     * To get a List of Pokemon from url
     * @param url url to get the list of Pokemon, Usually a Page
     *
     * @return Result of the Network Call [NetworkResult]
     */
    suspend fun getListOfPokemon(url: String): NetworkResult<PaginatedData<PokemonListItem>>

    /**
     * To get Pokemon details
     * @param url url to get the details of a pokemon
     *
     * @return Result of the Network Call [NetworkResult]
     */
    suspend fun getPokemonDetail(url: String): NetworkResult<PokemonDetail>

    /**
     * to Details of Pokemon Species
     *
     * @param url url to get the details of a pokemon species
     *
     * @return Result of the Network Call [NetworkResult]
     */
    suspend fun getSpeciesDetail(url: String): NetworkResult<SpeciesDetail>

    /**
     * To Evolution chain of a pokemon Species
     *
     * @param url url to get the details of a evolution chain pokemon species
     *
     * @return Result of the Network Call [NetworkResult]
     */
    suspend fun getEvolutionChain(url: String): NetworkResult<EvolutionChain>

}
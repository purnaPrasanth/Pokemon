package com.purna.pokemon.data.repo

import androidx.paging.LivePagedListBuilder
import com.purna.base.Dispatchers
import com.purna.base.Executors
import com.purna.pokemon.data.Error
import com.purna.pokemon.data.NetworkResult
import com.purna.pokemon.data.Success
import com.purna.pokemon.data.datasources.PokemonDataSourceContract
import com.purna.pokemon.data.entity.PokemonDetail
import com.purna.pokemon.data.entity.SpeciesDetail
import kotlinx.coroutines.coroutineScope

/**
 * A repo to fetch Data Related to Pokemon
 *
 * @param pokemonDataSource an implementation of [PokemonDataSourceContract] to fetch pokemon data.
 */
class PokemonRepo(
    private val pokemonDataSource: PokemonDataSourceContract,
    private val dispatchers: Dispatchers,
    private val executors: Executors
) {
    /**
     * to get a List of Pokemon. Initial Page
     */
    suspend fun getPokemonList() = coroutineScope {
        pokemonDataSource.getListOfPokemon()
    }

    /**
     * to get a List of Pokemon from url
     *
     * @param url url to get the list of Pokemon, Usually a Page
     */
    suspend fun getPokemonList(url: String) = coroutineScope {
        pokemonDataSource.getListOfPokemon(url)
    }

    /**
     * to get Paginated List Wrapped in LiveData
     */
    suspend fun getPaginatedPagedList() = coroutineScope {
        LivePagedListBuilder(
            PokemonDataSourceFactory(
                this@PokemonRepo,
                this.coroutineContext,
                dispatchers
            ), 20
        ).setFetchExecutor(executors.ioExecutor).build()
    }

    /**
     * To get Pokemon details
     * @param url url to get the details of a pokemon
     *
     * @return null if Network Call Fails else [PokemonDetail]
     */
    suspend fun getPokemonDetail(pokemonUrl: String): PokemonDetail? = coroutineScope {
        when (val response = pokemonDataSource.getPokemonDetail(pokemonUrl)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null
    }

    /**
     * to Details of Pokemon Species
     *
     * @param url url to get the details of a pokemon species
     *
     * @return null if Network Call Fails else [SpeciesDetail]
     */
    suspend fun getSpeciesDetail(speciesUrl: String): SpeciesDetail? = coroutineScope {
        when (val response = pokemonDataSource.getSpeciesDetail(speciesUrl)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null
    }

    /**
     * To Evolution chain of a pokemon Species
     *
     * @param url url to get the details of a evolution chain pokemon species
     *
     * @return null if Network Call Fails else [EvolutionChain]
     */
    suspend fun getEvolutionChain(url: String) = coroutineScope {
        when (val response = pokemonDataSource.getEvolutionChain(url)) {
            is Success -> response.data
            is Error -> null
        }
    }
}
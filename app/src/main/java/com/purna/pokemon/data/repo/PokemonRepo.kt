package com.purna.pokemon.data.repo

import androidx.paging.LivePagedListBuilder
import com.purna.base.Dispatchers
import com.purna.base.Executors
import com.purna.pokemon.data.Error
import com.purna.pokemon.data.Success
import com.purna.pokemon.data.datasources.PokemonDataSourceContract
import com.purna.pokemon.data.entity.PokemonDetail
import com.purna.pokemon.data.entity.SpeciesDetail
import kotlinx.coroutines.coroutineScope

class PokemonRepo(
    private val pokemonDataSource: PokemonDataSourceContract,
    private val dispatchers: Dispatchers,
    private val executors: Executors
) {
    suspend fun getPokemonList() = coroutineScope {
        pokemonDataSource.getListOfPokemon()
    }

    suspend fun getPokemonList(url: String) = coroutineScope {
        pokemonDataSource.getListOfPokemon(url)
    }

    suspend fun getPaginatedPagedList() = coroutineScope {
        LivePagedListBuilder(
            PokemonDataSourceFactory(
                this@PokemonRepo,
                this.coroutineContext,
                dispatchers
            ), 20
        ).setFetchExecutor(executors.ioExecutor).build()
    }

    suspend fun getPokemonDetail(pokemonUrl: String): PokemonDetail? = coroutineScope {
        when (val response = pokemonDataSource.getPokemonDetail(pokemonUrl)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null
    }

    suspend fun getSpeciesDetail(speciesUrl: String): SpeciesDetail? = coroutineScope {
        when (val response = pokemonDataSource.getSpeciesDetail(speciesUrl)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null
    }

    suspend fun getEvolutionChain(url: String) = coroutineScope {
        when (val response = pokemonDataSource.getEvolutionChain(url)) {
            is Success -> response.data
            is Error -> null
        }
    }
}
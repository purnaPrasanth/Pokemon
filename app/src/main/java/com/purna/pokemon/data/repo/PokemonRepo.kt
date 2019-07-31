package com.purna.pokemon.data.repo

import androidx.paging.LivePagedListBuilder
import com.purna.base.Dispatchers
import com.purna.pokemon.data.Error
import com.purna.pokemon.data.Success
import com.purna.pokemon.data.datasources.PokemonDataSourceContract
import kotlinx.coroutines.coroutineScope

class PokemonRepo(
    private val pokemonDataSource: PokemonDataSourceContract,
    private val dispatchers: Dispatchers
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
        ).build()
    }

    suspend fun getEvolutionUrl(pokemonUrl: String) = coroutineScope {
        val pokemonDetail = when (val response = pokemonDataSource.getPokemonDetail(pokemonUrl)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null

        val speciesDetail = when (val response = pokemonDataSource.getSpeciesDetail(pokemonDetail.species.url)) {
            is Success -> response.data
            is Error -> {
                response.exception.printStackTrace()
                null
            }
        } ?: return@coroutineScope null

        speciesDetail.evolutionChain.url
    }
}
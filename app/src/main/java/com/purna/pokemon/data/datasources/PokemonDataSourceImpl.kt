package com.purna.pokemon.data.datasources

import com.purna.pokemon.data.NetworkCallRunner
import com.purna.pokemon.data.NetworkResult
import com.purna.pokemon.data.SelfMapper
import com.purna.pokemon.data.entity.*
import com.purna.pokemon.httpwrapper.ClientWrapper

class PokemonDataSourceImpl(
    private val httpWrapper: ClientWrapper,
    private val netWorkRunner: NetworkCallRunner
) : PokemonDataSourceContract {

    override suspend fun getListOfPokemon(): NetworkResult<PaginatedData<PokemonListItem>> {
        return getListOfPokemon("https://pokeapi.co/api/v2/pokemon")
    }

    override suspend fun getListOfPokemon(url: String): NetworkResult<PaginatedData<PokemonListItem>> {
        return netWorkRunner.executeForResponse(
            mapper = SelfMapper(),
            request = { httpWrapper.getResource(PaginatedData.serializer(PokemonListItem.serializer()), url) })
    }

    override suspend fun getPokemonDetail(url: String): NetworkResult<PokemonDetail> {
        return netWorkRunner.executeForResponse(
            mapper = SelfMapper(),
            request = { httpWrapper.getResource(PokemonDetail.serializer(), url) })
    }

    override suspend fun getSpeciesDetail(url: String): NetworkResult<SpeciesDetail> {
        return netWorkRunner.executeForResponse(
            mapper = SelfMapper(),
            request = { httpWrapper.getResource(SpeciesDetail.serializer(), url) })
    }

    override suspend fun getEvolutionChain(url: String): NetworkResult<EvolutionChain> {
        return netWorkRunner.executeForResponse(
            mapper = SelfMapper(),
            request = { httpWrapper.getResource(EvolutionChain.serializer(), url) }
        )
    }
}
package com.purna.pokemon.instances

import com.purna.base.creational.BaseGenerator
import com.purna.base.creational.single
import com.purna.pokemon.data.NetworkCallRunner
import com.purna.pokemon.data.datasources.PokemonDataSourceContract
import com.purna.pokemon.data.datasources.PokemonDataSourceImpl
import com.purna.pokemon.data.repo.PokemonRepo

object DataInstances {
    private val pokemonRepoGenerator: BaseGenerator<PokemonRepo> = single {
        PokemonRepo(
            pokemonDataSourceContract,
            ExecutorInstances.appDispatchers
        )
    }

    private val pokemonDataSourceGenerator: BaseGenerator<PokemonDataSourceContract> = single {
        PokemonDataSourceImpl(
            httpWrapper = NetworkInstances.clientWrapper,
            netWorkRunner = networkCallRunner
        )
    }

    private val networkCallRunnerGenerator: BaseGenerator<NetworkCallRunner> = single {
        NetworkCallRunner()
    }

    val pokemonRepo: PokemonRepo
        get() = pokemonRepoGenerator.getInstance()

    private val pokemonDataSourceContract: PokemonDataSourceContract
        get() = pokemonDataSourceGenerator.getInstance()

    private val networkCallRunner: NetworkCallRunner
        get() = networkCallRunnerGenerator.getInstance()
}
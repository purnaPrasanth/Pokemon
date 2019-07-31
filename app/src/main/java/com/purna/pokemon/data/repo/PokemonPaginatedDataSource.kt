package com.purna.pokemon.data.repo

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.purna.base.Dispatchers
import com.purna.pokemon.data.Success
import com.purna.pokemon.data.entity.PokemonListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class PokemonDataSourceFactory(
    private val repo: PokemonRepo,
    private val parentContext: CoroutineContext,
    private val dispatchers: Dispatchers
) : DataSource.Factory<String, PokemonListItem>() {
    override fun create() = PokemonPaginatedDataSource(
        repo,
        parentContext,
        dispatchers
    )
}

class PokemonPaginatedDataSource(
    private val repo: PokemonRepo,
    private val parentContext: CoroutineContext,
    private val dispatchers: Dispatchers
) : PageKeyedDataSource<String, PokemonListItem>(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = parentContext

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, PokemonListItem>
    ) {
        runBlocking(dispatchers.ioDispatcher) {
            when (val response = repo.getPokemonList()) {
                is Success -> callback.onResult(response.data.results, response.data.previous, response.data.next)
            }
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PokemonListItem>) {
        runBlocking(dispatchers.ioDispatcher) {
            when (val response = repo.getPokemonList(params.key)) {
                is Success -> callback.onResult(response.data.results, response.data.next)
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PokemonListItem>) {
        runBlocking(dispatchers.ioDispatcher) {
            when (val response = repo.getPokemonList(params.key)) {
                is Success -> callback.onResult(response.data.results, response.data.previous)
            }
        }
    }
}
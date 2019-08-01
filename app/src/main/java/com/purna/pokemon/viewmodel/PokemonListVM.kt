package com.purna.pokemon.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.purna.base.Dispatchers
import com.purna.pokemon.baseandroid.BaseViewModel
import com.purna.pokemon.data.entity.PokemonListItem
import com.purna.pokemon.data.repo.PokemonRepo
import kotlinx.coroutines.runBlocking

class PokemonListVM(application: Application, pokemonRepo: PokemonRepo, dispatchers: Dispatchers) :
    BaseViewModel(application, dispatchers) {

    val pokemonData: LiveData<PagedList<PokemonListItem>> =
        runBlocking { pokemonRepo.getPaginatedPagedList() }
}

class PokemonViewModelFactory(
    private val application: Application,
    private val repo: PokemonRepo,
    private val dispatchers: Dispatchers
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonListVM(application, repo, dispatchers) as T
    }

}
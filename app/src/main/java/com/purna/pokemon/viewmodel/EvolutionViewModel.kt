package com.purna.pokemon.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purna.pokemon.baseandroid.BaseViewModel
import com.purna.pokemon.data.entity.EvolutionChainItem
import com.purna.pokemon.data.repo.PokemonRepo
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/

class EvolutionViewModel(
    application: Application,
    private val url: String,
    private val pokemonRepo: PokemonRepo
) : BaseViewModel(application) {
    private val stack: Stack<List<EvolutionChainItem>> = Stack()
    private val currentEvolutionLevelInternal = MediatorLiveData<List<EvolutionChainItem>>()
    val currentEvolutionLevel: LiveData<List<EvolutionChainItem>>
        get() = currentEvolutionLevelInternal

    init {
        getEvolution()
    }

    private fun getEvolution() = launch {
        val evolutionChain = pokemonRepo.getEvolutionChain(url) ?: return@launch
        currentEvolutionLevelInternal.postValue(listOf(evolutionChain.chain))
    }

    fun pokemonSelected(name: String): Boolean {
        val currentEvolutionLevel = currentEvolutionLevel.value!!

        val clickedEvolution = currentEvolutionLevel.find {
            it.species.name == name
        }!!

        stack.push(currentEvolutionLevel)
        return if (clickedEvolution.evolvesTo.isNotEmpty()) {
            currentEvolutionLevelInternal.postValue(clickedEvolution.evolvesTo)
            true
        } else {
            false
        }
    }

    fun canGoBackInEvolution(): Boolean {
        if (stack.isEmpty()) return false

        currentEvolutionLevelInternal.postValue(stack.pop())
        return true
    }


}

class EvolutionViewModelFactory(
    private val application: Application,
    private val url: String,
    private val repo: PokemonRepo
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EvolutionViewModel(application, url, repo) as T
    }

}
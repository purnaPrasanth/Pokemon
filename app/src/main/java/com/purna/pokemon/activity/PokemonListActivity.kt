package com.purna.pokemon.activity

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.pokemon.R
import com.purna.pokemon.adapter.PokemonListAdapter
import com.purna.pokemon.baseandroid.BaseActivity
import com.purna.pokemon.data.repo.PokemonRepo
import com.purna.pokemon.databinding.ActivityPokemonListBinding
import com.purna.pokemon.decorators.rv.VerticalSpaceDecorator
import com.purna.pokemon.instances.DataInstances
import com.purna.pokemon.viewmodel.PokemonListVM
import com.purna.pokemon.viewmodel.PokemonViewModelFactory
import kotlinx.coroutines.launch

class PokemonListActivity : BaseActivity<ActivityPokemonListBinding>(R.layout.activity_pokemon_list),
    AdapterView.OnItemClickListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this, PokemonViewModelFactory(application, DataInstances.pokemonRepo, dispatchers))
            .get(PokemonListVM::class.java)
    }

    private val pokemonRepo: PokemonRepo
        get() = DataInstances.pokemonRepo

    private val pokemonListAdapter: PokemonListAdapter by lazy { PokemonListAdapter(this) }

    override fun initUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = pokemonListAdapter
        binding.recyclerView.addItemDecoration(VerticalSpaceDecorator(12))
        pokemonListAdapter.onItemClickListener = this

        viewModel.pokemonData.observe(this, Observer {
            pokemonListAdapter.submitList(it)
        })
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val pokemon = viewModel.pokemonData.value?.get(p2) ?: return

        launch {
            val pokemonDetail = pokemonRepo.getPokemonDetail(pokemon.url) ?: return@launch
            val speciesDetail = pokemonRepo.getSpeciesDetail(pokemonDetail.species.url) ?: return@launch
            EvolutionListActivity.startActivity(
                this@PokemonListActivity,
                speciesDetail.evolutionChain.url,
                pokemon.name
            )
        }
    }
}

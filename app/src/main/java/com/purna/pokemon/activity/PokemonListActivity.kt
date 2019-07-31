package com.purna.pokemon.activity

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.purna.pokemon.R
import com.purna.pokemon.adapter.PokemonListAdapter
import com.purna.pokemon.baseandroid.BaseActivity
import com.purna.pokemon.databinding.ActivityPokemonListBinding
import com.purna.pokemon.http.VerticalSpaceDecorator
import com.purna.pokemon.instances.DataInstances
import com.purna.pokemon.viewmodel.PokemonListVM
import com.purna.pokemon.viewmodel.PokemonViewModelFactory
import kotlinx.coroutines.launch

class PokemonListActivity : BaseActivity<ActivityPokemonListBinding>(R.layout.activity_pokemon_list),
    AdapterView.OnItemClickListener {

    private val viewModel by lazy {
        ViewModelProviders.of(this, PokemonViewModelFactory(application, DataInstances.pokemonRepo))
            .get(PokemonListVM::class.java)
    }

    private val adapter: PokemonListAdapter by lazy { PokemonListAdapter(this) }

    override fun initUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(VerticalSpaceDecorator(12))
        adapter.onItemClickListener = this

        viewModel.paginaeddatSource.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val pokemonUrl = viewModel.paginaeddatSource.value?.get(p2)?.url ?: return

        launch {
            Toast.makeText(
                this@PokemonListActivity,
                DataInstances.pokemonRepo.getEvolutionUrl(pokemonUrl).orEmpty(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

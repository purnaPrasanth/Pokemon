package com.purna.pokemon

import android.util.Log
import com.purna.pokemon.baseandroid.BaseActivity
import com.purna.pokemon.databinding.ActivityPokemonListBinding
import com.purna.pokemon.instances.NetworkInstances
import kotlinx.coroutines.launch
import java.lang.Exception

class PokemonListActivity : BaseActivity<ActivityPokemonListBinding>(R.layout.activity_pokemon_list) {

    override fun initUI() {
        binding.something.text = "PokemonList"

        launch {
            try {
                val response = NetworkInstances.clientWrapper.makeRequest("https://pokeapi.co/api/v2/pokemon")
                Log.d("PokemonActivity", response)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}

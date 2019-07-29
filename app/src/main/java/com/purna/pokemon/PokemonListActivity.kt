package com.purna.pokemon

import android.util.Log
import com.purna.pokemon.baseandroid.BaseActivity
import com.purna.pokemon.data.PaginatedData
import com.purna.pokemon.databinding.ActivityPokemonListBinding
import com.purna.pokemon.http.connection.GetConnection
import com.purna.pokemon.instances.NetworkInstances
import kotlinx.coroutines.launch

class PokemonListActivity : BaseActivity<ActivityPokemonListBinding>(R.layout.activity_pokemon_list) {

    override fun initUI() {
        binding.something.text = "PokemonList"

        launch {
            val con = GetConnection("https://pokeapi.co/api/v2/pokemon")
            val response = NetworkInstances.httpClient.makeRequest(con, PaginatedData::class.java)
            Log.d("PokemonActivity", response.toString())
        }
    }
}

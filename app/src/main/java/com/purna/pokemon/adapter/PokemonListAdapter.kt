package com.purna.pokemon.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.purna.pokemon.R
import com.purna.pokemon.data.entity.PokemonListItem
import com.purna.pokemon.databinding.PokemonListItemBinding

class PokemonListAdapter(context: Context) : SingleTypeBaseRvAdapter<PokemonListItemBinding, PokemonListItem>(
    context,
    R.layout.pokemon_list_item,
    PokemonListItemDiffCallback()
) {
    override fun onBindViewHolder(binding: PokemonListItemBinding, position: Int) {
        binding.pokemonName.text = getItem(position)?.name.orEmpty()
    }
}

class PokemonListItemDiffCallback : DiffUtil.ItemCallback<PokemonListItem>() {
    override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.url == newItem.url
    }

}
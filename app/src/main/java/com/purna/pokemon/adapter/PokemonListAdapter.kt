package com.purna.pokemon.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.purna.pokemon.R
import com.purna.pokemon.data.entity.EvolutionChainItem
import com.purna.pokemon.data.entity.PokemonListItem
import com.purna.pokemon.databinding.PokemonListItemBinding

/**
 * [PaginatedSingleTypeBaseRvAdapter] to show a list of [PokemonListItem]
 */

class PokemonListAdapter(context: Context) : PaginatedSingleTypeBaseRvAdapter<PokemonListItemBinding, PokemonListItem>(
    context,
    R.layout.pokemon_list_item,
    PokemonListItemDiffCallback()
) {
    override fun onBindViewHolder(binding: PokemonListItemBinding, position: Int) {
        binding.pokemonName.text = getItem(position)?.name.orEmpty()
    }
}

/**
 * [DiffUtil.ItemCallback] implementation for [PokemonListItem]. To be used in calculating diff between to List
 */
class PokemonListItemDiffCallback : DiffUtil.ItemCallback<PokemonListItem>() {
    override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem): Boolean {
        return oldItem.url == newItem.url
    }

}
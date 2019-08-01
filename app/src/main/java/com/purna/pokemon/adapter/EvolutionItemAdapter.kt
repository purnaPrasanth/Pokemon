package com.purna.pokemon.adapter

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.purna.pokemon.R
import com.purna.pokemon.data.entity.EvolutionChainItem
import com.purna.pokemon.databinding.PokemonListItemBinding

/**
 * Created by Purna on 2019-07-31 as a part of Pokemon
 **/
class EvolutionItemAdapter(context: Context, private val clickedPokemon: String) :
    SingleTypeBaseRvAdapter<PokemonListItemBinding, EvolutionChainItem>(
        context,
        R.layout.pokemon_list_item,
        EvolutionListItemDiffCallback()
    ) {
    override fun onBindViewHolder(binding: PokemonListItemBinding, position: Int) {
        val evolutionListItem = getItem(position)
        binding.pokemonName.text = evolutionListItem?.species?.name.orEmpty()

        binding.pokemonName.setTextColor(
            if (evolutionListItem?.species?.name == clickedPokemon) {
                mContext.resources.getColor(R.color.green)
            } else {
                mContext.resources.getColor(R.color.slate)
            }
        )
    }
}

class EvolutionListItemDiffCallback : DiffUtil.ItemCallback<EvolutionChainItem>() {
    override fun areItemsTheSame(oldItem: EvolutionChainItem, newItem: EvolutionChainItem): Boolean {
        return oldItem.species.name == newItem.species.name
    }

    override fun areContentsTheSame(oldItem: EvolutionChainItem, newItem: EvolutionChainItem): Boolean {
        return oldItem.species.url == newItem.species.url
    }

}
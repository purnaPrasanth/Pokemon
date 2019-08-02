package com.purna.pokemon.util

import com.purna.pokemon.data.entity.PokemonListItem

/**
 * Created by Purna on 2019-08-02 as a part of Pokemon
 **/

val pokemonListItem1 = PokemonListItem(
    "bulbasaur",
    "https://pokeapi.co/api/v2/pokemon/1/"
)

val pokemonListItem2 = PokemonListItem(
    "ivysaur",
    "https://pokeapi.co/api/v2/pokemon/2/"
)

val pokemonListItem3 = PokemonListItem(
    "venusaur",
    "https://pokeapi.co/api/v2/pokemon/3/"
)

val pokemonListItem4 = PokemonListItem(
    "charmander",
    "https://pokeapi.co/api/v2/pokemon/4/"
)

val pokemonListItem5 = PokemonListItem(
    "charmeleon",
    "https://pokeapi.co/api/v2/pokemon/5/"
)

val listOfPokemon = listOf(pokemonListItem1, pokemonListItem2, pokemonListItem3, pokemonListItem4, pokemonListItem5)

val pokemonTestData = "{\"count\":964,\"next\":\"https://pokeapi.co/api/v2/pokemon?offset=20&limit=20\",\"previous\":null,\"results\":[{\"name\":\"bulbasaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/1/\"},{\"name\":\"ivysaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/2/\"},{\"name\":\"venusaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/3/\"},{\"name\":\"charmander\",\"url\":\"https://pokeapi.co/api/v2/pokemon/4/\"},{\"name\":\"charmeleon\",\"url\":\"https://pokeapi.co/api/v2/pokemon/5/\"},{\"name\":\"charizard\",\"url\":\"https://pokeapi.co/api/v2/pokemon/6/\"},{\"name\":\"squirtle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/7/\"},{\"name\":\"wartortle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/8/\"},{\"name\":\"blastoise\",\"url\":\"https://pokeapi.co/api/v2/pokemon/9/\"},{\"name\":\"caterpie\",\"url\":\"https://pokeapi.co/api/v2/pokemon/10/\"},{\"name\":\"metapod\",\"url\":\"https://pokeapi.co/api/v2/pokemon/11/\"},{\"name\":\"butterfree\",\"url\":\"https://pokeapi.co/api/v2/pokemon/12/\"},{\"name\":\"weedle\",\"url\":\"https://pokeapi.co/api/v2/pokemon/13/\"},{\"name\":\"kakuna\",\"url\":\"https://pokeapi.co/api/v2/pokemon/14/\"},{\"name\":\"beedrill\",\"url\":\"https://pokeapi.co/api/v2/pokemon/15/\"},{\"name\":\"pidgey\",\"url\":\"https://pokeapi.co/api/v2/pokemon/16/\"},{\"name\":\"pidgeotto\",\"url\":\"https://pokeapi.co/api/v2/pokemon/17/\"},{\"name\":\"pidgeot\",\"url\":\"https://pokeapi.co/api/v2/pokemon/18/\"},{\"name\":\"rattata\",\"url\":\"https://pokeapi.co/api/v2/pokemon/19/\"},{\"name\":\"raticate\",\"url\":\"https://pokeapi.co/api/v2/pokemon/20/\"}]}"


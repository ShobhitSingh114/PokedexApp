package com.example.pokedexapp.presentation.pokemon_list

import com.example.pokedexapp.domain.model.PokedexListEntry

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<PokedexListEntry> = emptyList(),
    val error: String = ""
)

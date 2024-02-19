package com.example.pokedexapp.presentation.pokemon_list

import com.example.pokedexapp.domain.model.PokemonList

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: PokemonList? = null,
    val error: String = ""
)

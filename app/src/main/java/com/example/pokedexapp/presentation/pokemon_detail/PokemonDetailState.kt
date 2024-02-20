package com.example.pokedexapp.presentation.pokemon_detail

import com.example.pokedexapp.data.remote.dto.PokemonDetailDto

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetailDto? = null,
    val error: String = ""
)

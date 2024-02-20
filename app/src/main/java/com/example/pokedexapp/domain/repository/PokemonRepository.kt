package com.example.pokedexapp.domain.repository

import com.example.pokedexapp.data.remote.dto.PokemonDetailDto
import com.example.pokedexapp.data.remote.dto.PokemonListDto
import com.example.pokedexapp.util.Resource

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDto
    suspend fun getPokemonInfoByName(name: String): Resource<PokemonDetailDto>
}
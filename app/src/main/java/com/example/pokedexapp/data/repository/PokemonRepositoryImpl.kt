package com.example.pokedexapp.data.repository

import com.example.pokedexapp.data.remote.PokedexApi
import com.example.pokedexapp.data.remote.dto.PokemonDetailDto
import com.example.pokedexapp.data.remote.dto.PokemonListDto
import com.example.pokedexapp.domain.repository.PokemonRepository
import com.example.pokedexapp.util.Resource
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokedexApi
) : PokemonRepository {
    override suspend fun getPokemonList(offset: Int, limit: Int): PokemonListDto {
        return api.getPokemonList(offset, limit)
    }

    override suspend fun getPokemonInfoByName(name: String): PokemonDetailDto {
        return api.getPokemonInfoByName(name)
    }
}
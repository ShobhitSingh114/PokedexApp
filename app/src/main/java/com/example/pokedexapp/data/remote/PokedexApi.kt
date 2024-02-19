package com.example.pokedexapp.data.remote

import com.example.pokedexapp.data.remote.dto.PokemonDetailDto
import com.example.pokedexapp.data.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {
    @GET("/api/v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListDto

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokemonInfoByName(
        @Path("pokemonName") name: String
    ): PokemonDetailDto
}
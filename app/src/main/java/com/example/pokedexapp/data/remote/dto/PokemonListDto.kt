package com.example.pokedexapp.data.remote.dto


data class PokemonListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)

//fun PokemonListDto.toPokemonList(): PokemonList {
//    return PokemonList(
//        name = results.map { it.name },
//        image = results.map { it.url }
//    )
//}
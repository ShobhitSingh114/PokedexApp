package com.example.pokedexapp.data.remote.dto

import com.example.pokedexapp.domain.model.PokemonDetail
import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val cries: Cries,
    val forms: List<Form>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndice>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<Any>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

fun PokemonDetailDto.toPokmonDetail(): PokemonDetail {
    return PokemonDetail(
//        image = ,
        name = name,
        id = id,
        height = height,
        weight = weight,
        type = types.map { it.type.name },
        baseStat = stats.map { it.baseStat },
        statName = stats.map { it.stat.name }
    )
}
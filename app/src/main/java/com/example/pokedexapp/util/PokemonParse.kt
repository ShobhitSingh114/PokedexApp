package com.example.pokedexapp.util

import androidx.compose.ui.graphics.Color
import com.example.pokedexapp.data.remote.dto.Stat
import com.example.pokedexapp.data.remote.dto.Type
import com.example.pokedexapp.presentation.ui.theme.AtkColor
import com.example.pokedexapp.presentation.ui.theme.DefColor
import com.example.pokedexapp.presentation.ui.theme.HPColor
import com.example.pokedexapp.presentation.ui.theme.SpAtkColor
import com.example.pokedexapp.presentation.ui.theme.SpDefColor
import com.example.pokedexapp.presentation.ui.theme.SpdColor
import com.example.pokedexapp.presentation.ui.theme.TypeBug
import com.example.pokedexapp.presentation.ui.theme.TypeDark
import com.example.pokedexapp.presentation.ui.theme.TypeDragon
import com.example.pokedexapp.presentation.ui.theme.TypeElectric
import com.example.pokedexapp.presentation.ui.theme.TypeFairy
import com.example.pokedexapp.presentation.ui.theme.TypeFighting
import com.example.pokedexapp.presentation.ui.theme.TypeFire
import com.example.pokedexapp.presentation.ui.theme.TypeFlying
import com.example.pokedexapp.presentation.ui.theme.TypeGhost
import com.example.pokedexapp.presentation.ui.theme.TypeGrass
import com.example.pokedexapp.presentation.ui.theme.TypeGround
import com.example.pokedexapp.presentation.ui.theme.TypeIce
import com.example.pokedexapp.presentation.ui.theme.TypeNormal
import com.example.pokedexapp.presentation.ui.theme.TypePoison
import com.example.pokedexapp.presentation.ui.theme.TypePsychic
import com.example.pokedexapp.presentation.ui.theme.TypeRock
import com.example.pokedexapp.presentation.ui.theme.TypeSteel
import com.example.pokedexapp.presentation.ui.theme.TypeWater
import java.util.Locale

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.lowercase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.lowercase(Locale.ROOT)) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}
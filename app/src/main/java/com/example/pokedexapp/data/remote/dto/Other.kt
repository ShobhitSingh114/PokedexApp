package com.example.pokedexapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val home: Home,
    val showdown: Showdown
)
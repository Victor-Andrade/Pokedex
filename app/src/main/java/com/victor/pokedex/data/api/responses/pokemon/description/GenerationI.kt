package com.victor.pokedex.data.api.responses.pokemon.description

import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redblue: RedBlue,
    val yellow: Yellow
)
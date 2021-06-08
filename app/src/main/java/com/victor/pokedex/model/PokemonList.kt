package com.victor.pokedex.model

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<SimplePokemonData>
    )
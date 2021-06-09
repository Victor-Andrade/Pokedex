package com.victor.pokedex.data.api.responses.pokemon

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<SimplePokemonData>
    )
package com.victor.pokedex.data.api.responses.pokemon.description

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)
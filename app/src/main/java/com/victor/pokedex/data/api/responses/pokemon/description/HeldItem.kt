package com.victor.pokedex.data.api.responses.pokemon.description

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)
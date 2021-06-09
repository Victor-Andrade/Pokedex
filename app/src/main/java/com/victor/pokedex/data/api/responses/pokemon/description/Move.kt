package com.victor.pokedex.data.api.responses.pokemon.description

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
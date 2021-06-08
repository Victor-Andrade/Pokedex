package com.victor.pokedex.data.api.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)
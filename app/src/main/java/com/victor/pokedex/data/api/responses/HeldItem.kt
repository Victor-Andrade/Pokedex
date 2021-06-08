package com.victor.pokedex.data.api.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)
package com.victor.pokedex.domain

import com.victor.pokedex.data.api.responses.pokemon.description.Pokemon
import com.victor.pokedex.data.repository.IPokemonRepository

class GetPokemon(
    private val repository: IPokemonRepository
) : IGetPokemonUseCase{
    override suspend fun getPokemonByName(name: String) = repository.getPokemonByName(name)
}

interface IGetPokemonUseCase{
    suspend fun getPokemonByName(name: String): Pokemon
}
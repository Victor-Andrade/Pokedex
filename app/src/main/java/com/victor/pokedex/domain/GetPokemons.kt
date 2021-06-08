package com.victor.pokedex.domain

import com.victor.pokedex.data.api.responses.pokemon.PokemonList
import com.victor.pokedex.data.repository.IPokemonRepository

class GetPokemons(
    private val pokemonRepository: IPokemonRepository
): IGetPokemonsUseCase {
    override suspend fun invoke(): PokemonList {
        return pokemonRepository.getPokemons()
    }
}

interface IGetPokemonsUseCase{
    suspend fun invoke(): PokemonList
}
package com.victor.pokedex.data.repository

import com.victor.pokedex.data.api.RetrofitInstance
import com.victor.pokedex.data.api.responses.pokemon.PokemonList
import com.victor.pokedex.data.api.responses.pokemon.description.Pokemon

class PokemonRepository: IPokemonRepository {
    override suspend fun getPokemons(): PokemonList = RetrofitInstance.api.getPokemons()

    override suspend fun getPokemonByName(name: String): Pokemon = RetrofitInstance.api.getPokemonByName(name)
}

interface IPokemonRepository{
    suspend fun getPokemons(): PokemonList
    suspend fun getPokemonByName(name: String): Pokemon
}
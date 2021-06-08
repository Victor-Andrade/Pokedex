package com.victor.pokedex.data.api

import com.victor.pokedex.data.api.responses.pokemon.description.Pokemon
import com.victor.pokedex.data.api.responses.pokemon.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path

interface PokedexAPI {

    @GET("pokemon/")
    suspend fun getPokemons(): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name")name: String): Pokemon

}
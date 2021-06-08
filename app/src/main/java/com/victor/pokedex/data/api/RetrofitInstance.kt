package com.victor.pokedex.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private const val BASE_URL = "https://pokeapi.co/api/v2/"
        private val retrofit by lazy{
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy{
            retrofit.create(PokedexAPI::class.java)
        }
    }
}
package com.victor.pokedex.presenter.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.data.api.responses.pokemon.PokemonList
import com.victor.pokedex.domain.IGetPokemonsUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPokemons: IGetPokemonsUseCase
) : ViewModel() {
    val pokemons = MutableLiveData<PokemonList>()

    fun getPokemons(){
        viewModelScope.launch {
            // Get data here from useCase
            pokemons.value = getPokemons.invoke()
        }
    }
}
package com.victor.pokedex.presenter.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.pokedex.data.api.responses.pokemon.description.Pokemon
import com.victor.pokedex.domain.IGetPokemonUseCase
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemon: IGetPokemonUseCase
): ViewModel() {
    val pokemon = MutableLiveData<Pokemon>()

    fun getPokemon(name: String){
        viewModelScope.launch{
            pokemon.value = getPokemon.getPokemonByName(name)

        }
    }


}
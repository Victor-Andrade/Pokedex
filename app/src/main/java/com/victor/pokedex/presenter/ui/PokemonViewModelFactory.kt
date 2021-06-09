package com.victor.pokedex.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victor.pokedex.domain.IGetPokemonUseCase
import com.victor.pokedex.presenter.ui.fragments.PokemonViewModel

class PokemonViewModelFactory(
    val getPokemon: IGetPokemonUseCase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonViewModel(getPokemon) as T
    }
}
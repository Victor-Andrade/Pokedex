package com.victor.pokedex.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victor.pokedex.domain.IGetPokemonsUseCase
import com.victor.pokedex.presenter.ui.fragments.MainViewModel

class MainViewModelFactory(
    val getPokemons: IGetPokemonsUseCase
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(getPokemons) as T
    }
}
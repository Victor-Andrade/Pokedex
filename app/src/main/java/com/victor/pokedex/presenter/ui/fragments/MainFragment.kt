package com.victor.pokedex.presenter.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.victor.pokedex.databinding.MainFragmentBinding
import com.victor.pokedex.data.api.responses.pokemon.PokemonList
import com.victor.pokedex.data.repository.PokemonRepository
import com.victor.pokedex.domain.GetPokemons
import com.victor.pokedex.presenter.ui.MainViewModelFactory
import com.victor.pokedex.presenter.ui.PokemonRecyclerAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var pokemonAdapter: PokemonRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val repository = PokemonRepository()
        val getPokemon = GetPokemons(repository)
        val factory = MainViewModelFactory(getPokemon)
        viewModel = ViewModelProvider(this, factory ).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            setupRecyclerView(it)
        })
        viewModel.getPokemons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(pokemons: PokemonList) {
        pokemonAdapter = PokemonRecyclerAdapter(pokemons.results)
        binding.recyclerView.adapter = pokemonAdapter
    }

}
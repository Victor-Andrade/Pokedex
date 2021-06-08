package com.victor.pokedex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.victor.pokedex.data.api.RetrofitInstance
import com.victor.pokedex.databinding.MainFragmentBinding
import com.victor.pokedex.model.PokemonList
import com.victor.pokedex.ui.PokemonRecyclerAdapter
import kotlinx.coroutines.launch

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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewModelScope.launch{
            val pokemons = RetrofitInstance.api.getPokemons()
            setupRecyclerView(pokemons)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView(pokemons: PokemonList) {
        pokemonAdapter = PokemonRecyclerAdapter(pokemons.results)
        binding.recyclerView.apply{
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}
package com.victor.pokedex.presenter.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bumptech.glide.Glide
import com.victor.pokedex.R
import com.victor.pokedex.data.api.RetrofitInstance
import com.victor.pokedex.databinding.FragmentPokemonBinding
import kotlinx.coroutines.launch

class PokemonFragment : Fragment(R.layout.fragment_pokemon) {

    private lateinit var pokemonName: String
    private lateinit var viewModel:ViewModel
    private val TAG = "PokemonFragment"
    private var _binding: FragmentPokemonBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateArgs()
        loadData()
    }

    private fun validateArgs() {
        val args = arguments?.getString("PokemonName")
        if(!args.isNullOrEmpty()){
            Log.d(TAG, args)
            pokemonName=args
        }else{
            Log.e(TAG,"No pokemon name received")
            Toast.makeText(context,"Error", Toast.LENGTH_LONG).show()
            FragmentNavigatorExtras()
            findNavController(this).popBackStack()
        }
    }

    private fun loadData() {
        viewModel.viewModelScope.launch {
            val pokemon = RetrofitInstance.api.getPokemonByName(pokemonName)
            val imageUrl = "https://pokeres.bastionbot.org/images/pokemon/${pokemon.id.toString()}.png"
            binding.idTv.text= pokemon.id.toString()
            val name = pokemon.name
            binding.pokemonNameTv.text = name[0].uppercaseChar() +name.substring(1)
            Glide.with(this@PokemonFragment).load(imageUrl).into(binding.pokemonMainImage)
        }
    }
}
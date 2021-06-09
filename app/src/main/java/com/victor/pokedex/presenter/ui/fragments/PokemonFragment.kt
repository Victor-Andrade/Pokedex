package com.victor.pokedex.presenter.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.bumptech.glide.Glide
import com.victor.pokedex.R
import com.victor.pokedex.data.repository.PokemonRepository
import com.victor.pokedex.databinding.FragmentPokemonBinding
import com.victor.pokedex.domain.GetPokemon
import com.victor.pokedex.presenter.ui.PokemonViewModelFactory

class PokemonFragment : Fragment(R.layout.fragment_pokemon) {

    private lateinit var pokemonName: String
    private lateinit var viewModel: PokemonViewModel
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
        validateArgs()
        val repository = PokemonRepository()
        val getPokemon = GetPokemon(repository)
        val factory = PokemonViewModelFactory(getPokemon)
        viewModel = ViewModelProvider(this, factory).get(PokemonViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner){
            val imageUrl = "https://pokeres.bastionbot.org/images/pokemon/${it.id}.png"
            binding.idTv.text= it.id.toString()
            val name = it.name[0].uppercaseChar() +it.name.substring(1)
            binding.pokemonNameTv.text = name
            Glide.with(this@PokemonFragment).load(imageUrl).into(binding.pokemonMainImage)
        }
        viewModel.getPokemon(pokemonName)
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
}
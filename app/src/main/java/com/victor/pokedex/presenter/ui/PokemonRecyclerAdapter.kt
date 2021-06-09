package com.victor.pokedex.presenter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.victor.pokedex.databinding.RecyclerItemBinding
import com.victor.pokedex.data.api.responses.pokemon.SimplePokemonData
import com.victor.pokedex.presenter.ui.fragments.MainFragmentDirections

class PokemonRecyclerAdapter(
    private val pokemons: List<SimplePokemonData>,
) : RecyclerView.Adapter<PokemonRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: SimplePokemonData){
            val id = if(data.url.endsWith("/")) {
                data.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                data.url.takeLastWhile { it.isDigit() }
            }
            val url = "https://pokeres.bastionbot.org/images/pokemon/${id}.png"
            binding.apply{
                nameTextView.text = data.name
                Glide.with(this.root)
                    .load(url)
                    .into(pokemonImage)
            }
            val click = createOnClickListener(binding, data.name)
            binding.root.setOnClickListener(click)
        }
    }

    private fun createOnClickListener(binding : RecyclerItemBinding, pokemonName: String): View.OnClickListener {
        return View.OnClickListener {
            val directions = MainFragmentDirections.actionMainFragmentToPokemonFragment(pokemonName)
            val extras = FragmentNavigatorExtras(binding.pokemonImage to "detailsPokemonImage")
            it.findNavController().navigate(directions, extras)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(RecyclerItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)

    }

    override fun getItemCount(): Int {
        return pokemons.size
    }
}

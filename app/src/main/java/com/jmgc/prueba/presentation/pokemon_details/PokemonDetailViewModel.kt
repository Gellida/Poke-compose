package com.jmgc.prueba.presentation.pokemon_details

import androidx.lifecycle.ViewModel
import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.use_case.PokemonDetailsUseCase
import com.jmgc.prueba.domain.use_case.SavePokemonsUseCase
import com.jmgc.prueba.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailsUseCase: PokemonDetailsUseCase
): ViewModel() {

    suspend fun getPokemonDetails(pokemonName: String): Resource<PokemonDto>{
        return pokemonDetailsUseCase.execute(pokemonName)
    }
}
package com.jmgc.prueba.domain.use_case

import android.util.Log
import com.jmgc.prueba.data.mappers.toDomainModel
import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.util.Resource
import javax.inject.Inject

class PokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(pokemonName: String): Resource<PokemonDto> {
        val response = try {
            val pokemonDetails = pokemonRepository.getPokemonDetails(pokemonName)
            Log.d("PokemonDetailes", pokemonDetails.toString())
            pokemonDetails
        } catch (e: Exception) {
            return Resource.Error("Un error en el caso de uso")
        }
        return Resource.Success(response)
    }


}
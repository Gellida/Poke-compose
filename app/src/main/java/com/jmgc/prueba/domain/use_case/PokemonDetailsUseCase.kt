package com.jmgc.prueba.domain.use_case

import android.util.Log
import com.jmgc.prueba.data.mappers.toDomainModel
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.util.Resource
import javax.inject.Inject

class PokemonDetailsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(pokemonName: String): Resource<Pokemon> {
        val response = try {
            val pokemonDetails = pokemonRepository.getPokemonDetails(pokemonName).toDomainModel()
            Log.d("PokemonDetailes", pokemonDetails.toString())
            pokemonDetails
        } catch (e: Exception) {
            return Resource.Error("Un error en el caso de uso")
        }
        return Resource.Success(response)
    }


}
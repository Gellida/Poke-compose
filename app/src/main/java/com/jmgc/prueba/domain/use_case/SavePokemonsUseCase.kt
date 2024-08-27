package com.jmgc.prueba.domain.use_case

import android.util.Log
import com.jmgc.prueba.data.local.PokemonDao
import com.jmgc.prueba.data.mappers.toEntity
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.util.Resource
import javax.inject.Inject

class SavePokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonDao: PokemonDao
) {

    suspend fun execute(): Resource<Unit> {
        return try {
            val pokemons = pokemonRepository.getPokemons(0,3)
            Log.d("PokemonSaveUseCase", pokemons.toString())

            val pokemonEntities = pokemons.map { it.toEntity() }
            Log.d("PokemonSaveUseCase", pokemonEntities.toString())
            pokemonDao.insertAll(pokemonEntities)
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error("Un error al guardar: ${e.message}")
        }
    }
}

package com.jmgc.prueba.domain.use_case

import android.util.Log
import com.jmgc.prueba.data.local.PokemonDao
import com.jmgc.prueba.data.mappers.toDomainModel
import com.jmgc.prueba.data.mappers.toEntity
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.util.Resource
import javax.inject.Inject

class FetchPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val pokemonDao: PokemonDao
) {

    suspend fun execute(offset: Int, limit: Int): Resource<List<Pokemon>> {
        return try {
            val pokemons = pokemonRepository.getPokemons(offset, limit)
            Log.d("SavePokemonsUseCase", "Pokemon names: $pokemons")

            val pokemonEntities = pokemons.map { pokemon ->
                val details = pokemonRepository.getPokemonDetails(pokemon.name)
                details
            }
            Log.d("SavePokemonsUseCase", "Pokemon entities: $pokemonEntities")

            /*


            pokemonDao.insertAll(pokemonEntities)

            val pokemonDomain = pokemonEntities.map {
                    pokemon ->
                val details = pokemonRepository.getPokemonDetails(pokemon.name)
                details.toDomainModel()
                
            }
 */
            Resource.Success(pokemons) // Devuelve la lista de Pokémon
        } catch (e: Exception) {
            Resource.Error("Un error en el caso de uso: ${e.message}")
        }
    }


}
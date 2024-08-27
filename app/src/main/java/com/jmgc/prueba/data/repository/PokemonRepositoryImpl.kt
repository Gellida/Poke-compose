package com.jmgc.prueba.data.repository

import com.jmgc.prueba.data.mappers.toDomainModel
import com.jmgc.prueba.data.mappers.toEntity
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.data.remote.api.PokeApiService
import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon> {
        val response = pokeApiService.getPokemons(offset, limit)
        return response.results.map { it.toDomainModel() }
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDto {
        val response = pokeApiService.getPokemon(pokemonName)
        return response
    }

    override suspend fun fetchAndSavePokemons(pokemon: Pokemon): Pokemon {
        TODO("Not yet implemented")
    }


}


package com.jmgc.prueba.data.repository

import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.data.remote.PokeApiService
import com.jmgc.prueba.domain.repository.PokemonRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokeApiService: PokeApiService
) : PokemonRepository {
    override suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon> {

        val response = pokeApiService.getPokemons(offset, limit)

        return response.results
    }
}


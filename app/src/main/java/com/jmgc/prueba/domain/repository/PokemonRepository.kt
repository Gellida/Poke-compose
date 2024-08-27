package com.jmgc.prueba.domain.repository


import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.model.Pokemon
import dagger.Provides


interface PokemonRepository {
    suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon>

    suspend fun getPokemonDetails(name:String): PokemonDto

    suspend fun fetchAndSavePokemons(pokemon: Pokemon):Pokemon
}


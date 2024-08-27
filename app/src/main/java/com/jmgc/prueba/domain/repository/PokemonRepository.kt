package com.jmgc.prueba.domain.repository


import com.jmgc.prueba.domain.model.Pokemon
import dagger.Provides


interface PokemonRepository {
    suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon>

    suspend fun getPokemonDetails(name:String): Pokemon

    suspend fun fetchAndSavePokemons(pokemon: Pokemon):Pokemon
}


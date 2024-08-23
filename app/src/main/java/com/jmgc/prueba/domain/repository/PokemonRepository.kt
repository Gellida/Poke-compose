package com.jmgc.prueba.domain.repository


import com.jmgc.prueba.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(offset: Int, limit: Int): List<Pokemon>
}


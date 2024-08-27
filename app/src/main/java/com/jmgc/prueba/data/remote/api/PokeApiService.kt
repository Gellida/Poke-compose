package com.jmgc.prueba.data.remote.api

import com.jmgc.prueba.data.remote.dto.PokemonList
import com.jmgc.prueba.data.remote.dto.PokemonResponse
import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonList

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name : String
    ): Pokemon
}





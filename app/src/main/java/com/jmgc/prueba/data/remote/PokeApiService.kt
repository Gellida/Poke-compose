package com.jmgc.prueba.data.remote

import com.jmgc.prueba.data.remote.dto.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeApiService {

    @GET("pokemon?")
    suspend fun getPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): PokemonResponse

    @GET("pokemon")
    suspend fun getPokemon(): PokemonResponse
}





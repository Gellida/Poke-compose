package com.jmgc.prueba.data.repository

import com.jmgc.prueba.data.remote.api.PokeApiService
import com.jmgc.prueba.data.remote.dto.PokemonList
import com.jmgc.prueba.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository1 @Inject constructor(
    private val pokeApiService: PokeApiService
) {
      suspend fun getPokemonList(limit:Int, offset:Int): Resource<PokemonList> {

         val response = try {
             pokeApiService.getPokemonList(limit,offset)
         } catch (e:Exception){
             return  Resource.Error("Un error en el repositorio")
         }
         return Resource.Success(response)
    }

}
package com.jmgc.prueba.domain.use_case

import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(offset: Int, limit: Int): List<Pokemon> {
        return pokemonRepository.getPokemons(offset, limit)
    }
}
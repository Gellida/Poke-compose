package com.jmgc.prueba.data.remote.dto

import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.domain.model.Pokemon

data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)
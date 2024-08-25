package com.jmgc.prueba.data.mappers

import com.jmgc.prueba.data.local.PokemonEntity
import com.jmgc.prueba.data.remote.dto.poke.AbilityXDto
import com.jmgc.prueba.data.remote.dto.poke.PokemonDto
import com.jmgc.prueba.data.remote.dto.poke.SpritesDto
import com.jmgc.prueba.data.remote.dto.poke.StatDto
import com.jmgc.prueba.domain.model.Ability
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.model.Sprites
import com.jmgc.prueba.domain.model.Stat

fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name ?: "Unknown",
        height = this.height ?: 0,
        weight = this.weight ?: 0
    )
}
fun Pokemon.toDomainModel(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight,
    )
}

fun PokemonDto.toDomainModel(): Pokemon {
    return Pokemon(
        id = this.id,
        name = this.name,
        height = this.height,
        weight = this.weight
    )
}

fun AbilityXDto.toDomainModel(): Ability {
    return Ability(
        name = this.name,
        url = this.url
    )
}

fun SpritesDto.toDomainModel(): Sprites {
    return Sprites(
        front_default = this.front_default,
        back_default = this.back_default,
        front_shiny = this.front_shiny,
        back_shiny = this.back_shiny,
        back_female = this.back_female.toString(),
        back_shiny_female = this.back_shiny,
        front_female = this.front_female.toString(),
        front_shiny_female = this.front_shiny_female.toString()
    )
}

fun StatDto.toDomainModel(): Stat {
    return Stat(
        name = this.stat.name,
        baseStat = this.base_stat,
        effort = this.effort
    )
}


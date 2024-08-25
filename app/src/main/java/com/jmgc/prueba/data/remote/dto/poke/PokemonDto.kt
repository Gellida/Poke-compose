package com.jmgc.prueba.data.remote.dto.poke

data class PokemonDto(
    val abilities: List<AbilityDto>,
    val base_experience: Int,
    val cries: Cries,
    val forms: List<Form>,
    val game_indices: List<GameIndice>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<MoveDto>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>,
    val past_types: List<Any>,
    val species: SpeciesDto,
    val sprites: SpritesDto,
    val stats: List<StatDto>,
    val types: List<TypeDto>,
    val weight: Int
)
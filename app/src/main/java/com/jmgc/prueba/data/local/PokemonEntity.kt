package com.jmgc.prueba.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey val id: Int?,
    val name: String,
    val height: Int,
    val weight: Int,
)
package com.jmgc.prueba.data.remote.dto

data class PokemonList (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>
)

data class Result (
    val name: String,
    val url: String){

}
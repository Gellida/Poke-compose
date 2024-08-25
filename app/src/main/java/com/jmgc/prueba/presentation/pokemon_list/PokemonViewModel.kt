package com.jmgc.prueba.presentation.pokemon_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.use_case.FetchPokemonsUseCase
import com.jmgc.prueba.domain.use_case.SavePokemonsUseCase
import com.jmgc.prueba.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val fetchPokemonsUseCase: FetchPokemonsUseCase,
    private val savePokemonsUseCase: SavePokemonsUseCase
) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
    loadPokemons()
    }


    private fun loadPokemons() {
        viewModelScope.launch {
            when (val result = fetchPokemonsUseCase.execute(offset = 0, limit = 3)) {
                is Resource.Success -> {
                    _pokemons.value = result.data ?: emptyList()
                }

                is Resource.Error -> {
                    _error.value = result.message
                }

                is Resource.Loading -> {
                }
            }
        }
    }

    /*
    private fun loadPokemons() {
        viewModelScope.launch {
            when (val result = getPokemonsUseCase.execute(offset = 0, limit = 25)) {
                is Resource.Success -> {
                    _pokemons.value = result.data ?: emptyList()
                }

                is Resource.Error -> {
                    _error.value = result.message
                }

                is Resource.Loading -> {
                    // manejar el estado de carga aquí
                }
            }
        }
    }
     */


    fun refreshData() {
        viewModelScope.launch {
            when (val result = savePokemonsUseCase.execute()
            ) {
                is Resource.Success -> {
                    Log.d("PokemonViewModel", "pokemones guardados")
                }

                is Resource.Error -> {
                    Log.d("PokemonViewModel", "pokemons error"+result.message)
                    _error.value = result.message
                }

                is Resource.Loading -> {
                    Log.d("PokemonViewModel", "pokemons loading")
                    // manejar el estado de carga aquí
                }
            }
        }
    }
}
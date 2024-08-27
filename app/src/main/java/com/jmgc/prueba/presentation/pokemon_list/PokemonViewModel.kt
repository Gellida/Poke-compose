package com.jmgc.prueba.presentation.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.jmgc.prueba.data.repository.PokemonRepository1
import com.jmgc.prueba.domain.model.PokedexListEntry
import com.jmgc.prueba.domain.model.Pokemon
import com.jmgc.prueba.domain.repository.PokemonRepository
import com.jmgc.prueba.domain.use_case.FetchPokemonsUseCase
import com.jmgc.prueba.domain.use_case.SavePokemonsUseCase
import com.jmgc.prueba.util.Constants.PAGE_SIZE
import com.jmgc.prueba.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository1,
    private val fetchPokemonsUseCase: FetchPokemonsUseCase,
    private val savePokemonsUseCase: SavePokemonsUseCase
) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var  curPage = 0
    var pokemonList  = mutableStateOf<List<PokedexListEntry>>(listOf())

    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
    loadPokemonPaginated()
    }

    fun loadPokemonPaginated(){
        viewModelScope.launch {
            isLoading.value = true
            val result = pokemonRepository.getPokemonList(PAGE_SIZE, curPage* PAGE_SIZE)
            when(result) {
                is Resource.Success -> {

                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count

                    val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith(suffix = "/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                    }

                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokedexEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

                is Resource.Loading -> TODO()
            }


        }
    }

    fun calcDominant(drawable: Drawable, onFinish: (Color) -> Unit){
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Palette.from(bmp).generate(){ palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }

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
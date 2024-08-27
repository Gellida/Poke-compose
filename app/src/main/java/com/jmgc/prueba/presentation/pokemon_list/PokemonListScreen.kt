package com.jmgc.prueba.presentation.pokemon_list

import android.widget.Space
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.room.Index
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jmgc.prueba.R
import com.jmgc.prueba.domain.model.PokedexListEntry
import com.jmgc.prueba.domain.model.Pokemon


@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonViewModel
) {
    val pokemons by viewModel.pokemons.collectAsState()
    var searchText by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(36.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_logo_pokemon),
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            SearchBar(
                hint = "Buscar...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                searchText = it // apartado para buscar por nombre
                // viewModel.searchPokemons(it)
            }

            Spacer(modifier = Modifier.height(16.dp))
            PokemonList(navController = navController)
            /*
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(pokemons) { pokemon ->
                    PokemonListItem(pokemon)
                }
            }
             */

        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    //isHintDisplayed = it != FocusState.Active
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }

    }
}

@Composable
fun PokemonListItem(pokemon: Pokemon) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Manejar clic aquí */ }
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = pokemon.name, style = MaterialTheme.typography.bodyMedium)
        Text(text = pokemon.height.toString(), style = MaterialTheme.typography.bodyMedium)
        Text(text = pokemon.weight.toString(), style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }

    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }

        items(itemCount) { index ->
            if (index >= itemCount - 1 && !endReached && !isLoading) {
                viewModel.loadPokemonPaginated()
            }
            PokedexRow(rowIndex = index, entries = pokemonList, navController = navController)
        }

    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {

    }


}

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonViewModel = hiltViewModel()

) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    val dominantColor = remember {
        mutableStateOf(defaultDominantColor)
    }
    Box(

        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)) // Combine clip and background modifiers
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    listOf(dominantColor.value, defaultDominantColor)
                )
            )


            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor.value.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(entry.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = entry.pokemonName,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.BottomCenter),
            onSuccess = { state ->
                viewModel.calcDominant(state.result.drawable) { color ->
                    dominantColor.value = color
                }
            })
        Text(
            text = entry.pokemonName,
            fontSize = 20.sp,

            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter).padding(top = 8.dp)

        )
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexListEntry>,
    navController: NavController
) {
    Column {
        Row {
            PokedexEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))


            if (entries.size >= rowIndex * 2 + 2) {
                PokedexEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}


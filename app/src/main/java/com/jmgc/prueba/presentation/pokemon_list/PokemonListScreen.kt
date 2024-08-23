package com.jmgc.prueba

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmgc.prueba.domain.model.Pokemon



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(modifier: Modifier = Modifier, viewModel: PokemonViewModel) {
    val pokemons by viewModel.pokemons.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pokémon List") })
        }
    ) { paddingValues ->
        LazyColumn(modifier = modifier.padding(paddingValues)) {

            items(pokemons) { pokemon ->
                PokemonListItem(pokemon)
            }
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
        /*
          AsyncImage(
            model = pokemon.sprites.front_default,
            contentDescription = "${pokemon.name} sprite",
            modifier = Modifier.size(64.dp).clip(CircleShape)
        )
         */

        Spacer(modifier = Modifier.width(8.dp))
        Text(text = pokemon.name, style = MaterialTheme.typography.bodyMedium)
    }
}
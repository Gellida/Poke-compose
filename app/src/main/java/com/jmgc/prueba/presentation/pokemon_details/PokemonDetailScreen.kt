package com.jmgc.prueba.presentation.pokemon_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jmgc.prueba.R
import com.jmgc.prueba.presentation.pokemon_list.PokemonViewModel
import java.lang.reflect.Modifier

//@Composable
fun PokemonDetailScreen(pokemonId: Int, viewModel: PokemonViewModel) {
    /*


    val pokemon by viewModel.getPokemonById(pokemonId).observeAsState()

    pokemon?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            PokemonImage(
                imageUrl = it.imageUrl,
                name = it.name,
                placeholder = painterResource(id = R.drawable.placeholder),
                backgroundColor = Color.Gray,
                textColor = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it.name, style = MaterialTheme.typography.h4)
            Text(text = "Height: ${it.height}")
            Text(text = "Weight: ${it.weight}")
            Text(text = "Types: ${it.types.joinToString(", ")}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.toggleFavorite(it) }) {
                Text(text = if (it.isFavorite) "Unfavorite" else "Favorite")
            }
        }
    }

     */
}

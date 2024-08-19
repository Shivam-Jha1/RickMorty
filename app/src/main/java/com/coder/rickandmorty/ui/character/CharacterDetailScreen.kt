package com.coder.rickandmorty.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coder.rickandmorty.viewmodel.CharacterViewModel

@Composable
fun CharacterDetailScreen(characterId: Int, viewModel: CharacterViewModel = viewModel()) {
    viewModel.selectCharacter(characterId)
    val character = viewModel.selectedCharacter.collectAsState().value

    if (character == null) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }

    character?.let { character ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            if (character.image.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(character.image),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(200.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray)
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(
                text = "Name: ${character.name}",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Status: ${character.status}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Species: ${character.species}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Gender: ${character.gender}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Origin: ${character.origin.name}",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Location: ${character.location.name}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
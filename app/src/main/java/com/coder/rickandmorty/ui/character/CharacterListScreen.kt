package com.coder.rickandmorty.ui.character

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.coder.rickandmorty.viewmodel.CharacterViewModel
import kotlinx.coroutines.flow.filter

@Composable
fun CharacterListScreen(navController: NavHostController, viewModel: CharacterViewModel) {
    val characters by viewModel.characters.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(characters) { character ->
            CharacterItem(character = character) {
                navController.navigate("detail/${character.id}")
            }
        }

        item {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .filter { visibleItem ->
                visibleItem != null && visibleItem.index >= characters.size - 1
            }
            .collect {
                viewModel.loadNextPage()
            }
    }
}

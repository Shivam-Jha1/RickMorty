package com.coder.rickandmorty.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.coder.rickandmorty.ui.character.CharacterDetailScreen
import com.coder.rickandmorty.ui.character.CharacterListScreen
import com.coder.rickandmorty.viewmodel.CharacterViewModel

@Composable
fun Navigation(navController: NavHostController, viewModel: CharacterViewModel) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            CharacterListScreen(navController = navController, viewModel = viewModel)
        }
        composable("detail/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toIntOrNull()
            if (characterId != null) {
                CharacterDetailScreen(characterId = characterId, viewModel = viewModel)
            } else {
                navController.popBackStack()
            }
        }
    }
}





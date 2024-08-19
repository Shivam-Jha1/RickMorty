package com.coder.rickandmorty.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.coder.rickandmorty.ui.navigation.Navigation
import com.coder.rickandmorty.viewmodel.CharacterViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val viewModel: CharacterViewModel = viewModel()
    val navController = rememberNavController()
    Navigation(navController = navController,viewModel = viewModel)
}
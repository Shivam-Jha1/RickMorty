package com.coder.rickandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coder.rickandmorty.data.model.Character
import com.coder.rickandmorty.data.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository()

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _selectedCharacter = MutableStateFlow<Character?>(null)
    val selectedCharacter: StateFlow<Character?> = _selectedCharacter

    private var currentPage = 1
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchCharacters()
    }

    fun fetchCharacters() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newCharacters = repository.getCharacters(currentPage)
                _characters.value = _characters.value + newCharacters
                currentPage++
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    Log.e("CharacterViewModel", "Resource not found (404)")
                } else {
                    Log.e("CharacterViewModel", "HTTP error: ${e.message()}")
                }
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Unexpected error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }


    fun loadNextPage() {
        if (!_isLoading.value) {
            fetchCharacters()
        }
    }

    fun selectCharacter(id: Int) {
        if (id <= 0) {
            Log.e("CharacterViewModel", "Invalid character ID: $id")
            _selectedCharacter.value = null
            return
        }
        viewModelScope.launch {
            try {
                val character = repository.getCharacterById(id)
                _selectedCharacter.value = character
            } catch (e: HttpException) {
                if (e.code() == 404) {
                    Log.e("CharacterViewModel", "Character not found with ID: $id")
                } else {
                    Log.e("CharacterViewModel", "HTTP error: ${e.message}")
                }
                _selectedCharacter.value = null
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Unexpected error: ${e.message}")
                _selectedCharacter.value = null
            }
        }
    }
}

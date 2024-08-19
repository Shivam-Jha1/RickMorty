package com.coder.rickandmorty.data.repository

import com.coder.rickandmorty.data.network.RetrofitInstance
import com.coder.rickandmorty.data.model.Character

class CharacterRepository {
    private val apiService = RetrofitInstance.api

    suspend fun getCharacters(page: Int): List<Character> {
        val response = apiService.getCharacters(page)
        return response.results
    }

    suspend fun getCharacterById(id: Int): Character {
        return apiService.getCharacterById(id)
    }
}


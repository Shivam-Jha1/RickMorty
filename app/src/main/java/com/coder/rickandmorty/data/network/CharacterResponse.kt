package com.coder.rickandmorty.data.network

import com.coder.rickandmorty.data.model.Character
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("results") val results: List<Character>,
    @SerializedName("info") val info: PageInfo
)

data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

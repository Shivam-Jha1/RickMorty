package com.coder.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    @SerializedName("image") val image: String
)

data class Location(
    val name: String,
    val url: String
)


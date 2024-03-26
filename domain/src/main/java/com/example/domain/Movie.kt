package com.example.domain

data class Movie(
    var id: Int,
    var name: String,
    var image: String,
    var overview: String,
    var userScore: Float,
    var trailer: String,
    var isWatched: Boolean = false,
)
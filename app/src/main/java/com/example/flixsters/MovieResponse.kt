package com.example.flixsters

data class MovieResponse (
    val page: Int,
    val results: List<Movie>
)
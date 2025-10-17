package com.example.flixsters

import com.google.gson.annotations.SerializedName

private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w342"

data class Movie(
    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    private val posterPath: String?
) {
    /**
     * Constructs the full URL for the movie's poster image.
     * Returns null if the poster path is missing.
     */
    fun posterUrl(): String? {
        return if (posterPath != null) {
            "$BASE_POSTER_URL$posterPath"
        } else {
            null
        }
    }
}

package com.example.flixsters

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
    ): Response<MovieResponse>
}
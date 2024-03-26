package com.example.movies.data.server

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("append_to_response") appendToResponse: String = "watch/providers,videos,credits"
    ): Movie

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String = "US"
    ): MovieListResult
}
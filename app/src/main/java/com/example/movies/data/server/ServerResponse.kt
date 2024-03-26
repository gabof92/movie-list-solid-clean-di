package com.example.movies.data.server

import com.squareup.moshi.Json

data class Movie(
    var id: Int,

    @Json(name = "title") var name: String,
    @Json(name = "overview") var overview: String?,
    @Json(name = "poster_path") var image: String?,
    @Json(name = "vote_average") var userScore: Float,
    @Json(name = "adult") var isAdult: Boolean,
    @Json(name = "videos") var videoResponse: VideoResults?,
    @Transient var isWatched: Boolean = false,
)

data class VideoResults(
    @Json(name = "results") val videos: List<VideoJson>,
)

data class MovieListResult(
    @Json(name = "results") val movies: List<Movie>
)

data class VideoJson(
    val id: String,
    val name: String,
    val site: String,
    val type: String,
    val key: String,
    val size: Int,
    @Json(name = "official") val isOfficial: Boolean,
){

    companion object {
        const val SITE_YOUTUBE = "YouTube"
        const val TYPE_TRAILER = "Trailer"
    }
}

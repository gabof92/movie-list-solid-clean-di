package com.example.movies.data.server

import arrow.core.Either
import com.example.data.datasource.MovieRemoteDataSource
import com.example.domain.Error
import com.example.movies.data.server.Movie as RemoteMovie
import com.example.domain.Movie
import com.example.movies.data.tryCall
import com.example.movies.di.ApiKey
import javax.inject.Inject

class MovieServerDataSource  @Inject constructor(@ApiKey private val apiKey: String) : MovieRemoteDataSource {

    override suspend fun getMovie(id: Int): Either<Error, Movie> = tryCall {
        RemoteConnection
            .retrofitService
            .getMovie(id, apiKey)
            .toDomainModel()
    }

    override suspend fun getMovieList(): Either<Error, List<Movie>> = tryCall {
        RemoteConnection
            .retrofitService
            .popularMovies(apiKey)
            .movies.toDomainModel()
    }
}

fun List<RemoteMovie>.toDomainModel(): List<Movie> = map { it.toDomainModel() }

fun RemoteMovie.toDomainModel(): Movie {
    var trailerKey = ""
    videoResponse?.videos?.let { trailerKey = it.getBestVideo() }
    return Movie(
        id,
        name,
        image ?: "",
        overview ?: "",
        userScore,
        trailerKey,
        false
    )
}

fun List<VideoJson>.getBestVideo(): String {
    val video = find {
        (it.site == VideoJson.SITE_YOUTUBE)
                &&
                (it.type == VideoJson.TYPE_TRAILER)
    }
    var key = ""
    video?.let { key = it.key }
    return key
}
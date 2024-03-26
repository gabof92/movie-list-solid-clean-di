package com.example.data.datasource

import arrow.core.Either
import com.example.domain.Movie
import com.example.domain.Error

interface MovieRemoteDataSource {
    suspend fun getMovie(id: Int): Either<Error, Movie>

    suspend fun getMovieList(): Either<Error, List<Movie>>
}
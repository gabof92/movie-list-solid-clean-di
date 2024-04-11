package com.example.movies.data.database

import com.example.data.datasource.MovieLocalDataSource
import com.example.domain.Error
import com.example.movies.data.tryCall
import com.example.movies.data.database.Movie as DbMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRoomDataSource @Inject constructor(private val movieDao: MovieDao) : MovieLocalDataSource {

    override val movies: Flow<List<com.example.domain.Movie>> = movieDao.getAll().map { it.toDomainModel() }

    override suspend fun saveFromRemote(movies: List<com.example.domain.Movie>): Error? = tryCall{
        movieDao.insertWithoutWatched(movies.fromDomainModel())
    }.fold(
        ifLeft = {it},
        ifRight = {null}
    )

    override suspend fun save(movies: List<com.example.domain.Movie>): Error? = tryCall {
        movieDao.insert(movies.fromDomainModel())
    }.fold(
        ifLeft = {it},
        ifRight = {null}
    )

    override fun getMovie(id: Int) = movieDao.getMovie(id).map { it.toDomainModel() }

}

private fun List<DbMovie>.toDomainModel(): List<com.example.domain.Movie> = map { it.toDomainModel() }

private fun DbMovie.toDomainModel(): com.example.domain.Movie = com.example.domain.Movie(
    id,
    name,
    image,
    overview,
    userScore,
    trailer,
    isWatched
)

private fun List<com.example.domain.Movie>.fromDomainModel(): List<DbMovie> = map { it.fromDomainModel() }

private fun com.example.domain.Movie.fromDomainModel(): DbMovie = DbMovie(
    id,
    name,
    image,
    overview,
    userScore,
    trailer,
    isWatched
)
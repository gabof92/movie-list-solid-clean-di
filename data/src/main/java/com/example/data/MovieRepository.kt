package com.example.data

import com.example.data.datasource.MovieLocalDataSource
import com.example.data.datasource.MovieRemoteDataSource
import com.example.domain.Movie
import kotlinx.coroutines.flow.Flow
import com.example.domain.Error

class MovieRepository(
    private var localDataSource: MovieLocalDataSource,
    private var remoteDataSource: MovieRemoteDataSource
) {

    val movies = localDataSource.movies
    lateinit var movie: Flow<Movie>

    suspend fun requestMovies(): Error? {
        val movieList = remoteDataSource.getMovieList()
        movieList.fold(ifLeft = { return it }) {
            localDataSource.saveFromRemote(it)
        }
        return null
    }

    suspend fun requestMovie(id: Int): Error? {
        val movie = remoteDataSource.getMovie(id)
        movie.fold(ifLeft = {return it}){
            //ifRight
            localDataSource.saveFromRemote(listOf(it))
        }
        //no error
        return null
    }

    suspend fun switchWatched(movie: Movie): Error? {
        val updatedMovie = movie.copy(isWatched = !movie.isWatched)
        return localDataSource.save(listOf(updatedMovie))
    }
}

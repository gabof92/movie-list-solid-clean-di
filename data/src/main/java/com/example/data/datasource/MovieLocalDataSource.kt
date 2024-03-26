package com.example.data.datasource

import com.example.domain.Movie
import kotlinx.coroutines.flow.Flow
import com.example.domain.Error

interface MovieLocalDataSource {
    val movies: Flow<List<Movie>>

    suspend fun saveFromRemote(movies: List<Movie>): Error?

    suspend fun save(movies: List<Movie>): Error?
    
    fun getMovie(id: Int): Flow<Movie>
}
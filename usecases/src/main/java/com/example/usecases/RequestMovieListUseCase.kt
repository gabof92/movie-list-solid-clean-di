package com.example.usecases

import com.example.data.MovieRepository

class RequestMovieListUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke() = repository.requestMovies()

}
package com.example.usecases

import com.example.data.MovieRepository

class RequestMovieUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int) = repository.requestMovie(id)
}
package com.example.usecases

import com.example.data.MovieRepository

class GetMovieListUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.movies
}
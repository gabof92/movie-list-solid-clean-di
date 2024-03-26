package com.example.usecases

import com.example.data.MovieRepository

class SwitchWatchedUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: com.example.domain.Movie) = repository.switchWatched(movie)
}
package com.example.usecases

import com.example.data.MovieRepository
import javax.inject.Inject

class SwitchWatchedUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(movie: com.example.domain.Movie) = repository.switchWatched(movie)
}
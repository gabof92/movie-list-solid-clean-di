package com.example.usecases

import com.example.data.MovieRepository
import javax.inject.Inject

class RequestMovieUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int) = repository.requestMovie(id)
}
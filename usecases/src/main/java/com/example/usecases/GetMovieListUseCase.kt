package com.example.usecases

import com.example.data.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke() = repository.movies
}
package com.example.usecases

import com.example.data.MovieRepository
import javax.inject.Inject

class RequestMovieListUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke() = repository.requestMovies()

}
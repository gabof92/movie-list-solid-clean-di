package com.example.movies.di

import com.example.data.MovieRepository
import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieListUseCase
import com.example.usecases.RequestMovieUseCase
import com.example.usecases.SwitchWatchedUseCase
import dagger.Module
import dagger.Provides

/*
Since we only need one instance we use an object,
but it can also work using a Class.
 */
@Module
object UseCaseModule {

    /*These functions will provide the
    parameters for our UseCase classes*/

    @Provides
    fun provideGetMovieListUseCase(moviesRepository: MovieRepository) =
        GetMovieListUseCase(moviesRepository)

    @Provides
    fun provideRequestMovieListUseCase(moviesRepository: MovieRepository) =
        RequestMovieListUseCase(moviesRepository)

    @Provides
    fun provideRequestMovieUseCase(moviesRepository: MovieRepository) =
        RequestMovieUseCase(moviesRepository)

    @Provides
    fun provideSwitchWatchedUseCase(moviesRepository: MovieRepository) =
        SwitchWatchedUseCase(moviesRepository)

}
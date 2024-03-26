package com.example.movies.di

import com.example.data.MovieRepository
import com.example.data.datasource.MovieLocalDataSource
import com.example.data.datasource.MovieRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @Provides
    fun provideMovieRepository(
        localDataSource: MovieLocalDataSource,
        remoteDataSource: MovieRemoteDataSource
    ) = MovieRepository(localDataSource, remoteDataSource)

}
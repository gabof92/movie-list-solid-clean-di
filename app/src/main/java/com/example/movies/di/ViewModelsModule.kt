package com.example.movies.di

import com.example.movies.ui.detail.DetailViewModelFactory
import com.example.movies.ui.list.ListViewModel
import com.example.movies.ui.list.ListViewModelFactory
import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieListUseCase
import com.example.usecases.RequestMovieUseCase
import com.example.usecases.SwitchWatchedUseCase
import dagger.Module
import dagger.Provides

@Module
object ViewModelsModule {

    /*We're providing the dependencies for the Factory
    not the ViewModel itself*/

    @Provides
    fun provideListViewModelFactory(
        getMovieListUseCase: GetMovieListUseCase,
        requestMovieListUseCase: RequestMovieListUseCase
    ) = ListViewModelFactory(getMovieListUseCase, requestMovieListUseCase)

    @Provides
    fun provideDetailViewModelFactory(
        getMovieListUseCase: GetMovieListUseCase,
        requestMovieUseCase: RequestMovieUseCase,
        switchWatchedUseCase: SwitchWatchedUseCase
    ) = DetailViewModelFactory(
        getMovieListUseCase,
        requestMovieUseCase,
        switchWatchedUseCase,
        -1
    )
}
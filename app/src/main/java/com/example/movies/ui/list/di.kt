package com.example.movies.ui.list

import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class ListFragmentModule{
    /*We're providing the dependencies for the Factory
    not the ViewModel itself*/
    @Provides
    fun provideListViewModelFactory(
        getMovieListUseCase: GetMovieListUseCase,
        requestMovieListUseCase: RequestMovieListUseCase
    ) = ListViewModelFactory(getMovieListUseCase, requestMovieListUseCase)
}

@Subcomponent(modules = [ListFragmentModule::class])
interface ListFragmentComponent{
    val listViewModelFactory: ListViewModelFactory
}
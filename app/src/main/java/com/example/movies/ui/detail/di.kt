package com.example.movies.ui.detail

import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieUseCase
import com.example.usecases.SwitchWatchedUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailFragmentModule(private val movieId :Int){
    /*We're providing the dependencies for the Factory
        not the ViewModel itself*/
    @Provides
    fun provideDetailViewModelFactory(
        getMovieListUseCase: GetMovieListUseCase,
        requestMovieUseCase: RequestMovieUseCase,
        switchWatchedUseCase: SwitchWatchedUseCase
    ) = DetailViewModelFactory(
        getMovieListUseCase,
        requestMovieUseCase,
        switchWatchedUseCase,
        movieId
    )
}

@Subcomponent(modules = [DetailFragmentModule::class])
interface DetailFragmentComponent{
    val detailViewModelFactory: DetailViewModelFactory
}
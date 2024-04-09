package com.example.movies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movies.data.toError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieUseCase
import com.example.usecases.SwitchWatchedUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update

class DetailViewModel(
    //not private val because it's only used in init
    getMovieListUseCase: GetMovieListUseCase,
    private val requestMovieUseCase: RequestMovieUseCase,
    private val switchWatchedUseCase: SwitchWatchedUseCase,
    private val movieId: Int
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val movieList = getMovieListUseCase()
            movieList
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { movies -> _state.update {
                    UiState(movie = movies.first { movie -> movie.id == movieId })
                } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val error = requestMovieUseCase(movieId)
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    fun onWatchedSwitched() {
        viewModelScope.launch {
            _state.value.movie?.let {
                switchWatchedUseCase(it)
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: com.example.domain.Movie? = null,
        val error: com.example.domain.Error? = null
    )

}

class DetailViewModelFactory @AssistedInject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val requestMovieUseCase: RequestMovieUseCase,
    private val switchWatchedUseCase: SwitchWatchedUseCase,
    //This marks that we will provide it manually
    @Assisted private val movieId: Int
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return DetailViewModel(
                getMovieListUseCase,
                requestMovieUseCase,
                switchWatchedUseCase,
                movieId
            ) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

@AssistedFactory
interface DetailViewModelAssistedFactory{
    fun create(movieId: Int): DetailViewModelFactory
}
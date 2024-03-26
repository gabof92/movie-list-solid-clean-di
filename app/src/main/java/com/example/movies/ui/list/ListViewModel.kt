package com.example.movies.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movies.data.toError
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.usecases.GetMovieListUseCase
import com.example.usecases.RequestMovieListUseCase

class ListViewModel(
    //not private val because it's only used in init
    getMovieListUseCase: GetMovieListUseCase,
    private val requestMovieListUseCase: RequestMovieListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getMovieListUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { movies -> _state.update { UiState(movies = movies) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val error = requestMovieListUseCase()
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movies: List<com.example.domain.Movie>? = null,
        val error: com.example.domain.Error? = null
    )
}

class ListViewModelFactory(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val requestMovieListUseCase: RequestMovieListUseCase
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")

            return ListViewModel(getMovieListUseCase, requestMovieListUseCase) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
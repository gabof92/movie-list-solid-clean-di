package com.example.movies.ui.list

import androidx.navigation.NavController
import com.example.domain.Movie

class ListState(
    private val navController: NavController
) {
    fun navigateTo(movie: com.example.domain.Movie) {
        val navAction = ListFragmentDirections
            .actionFirstFragmentToSecondFragment(movie.id)
        navController.navigate(navAction)
    }
}
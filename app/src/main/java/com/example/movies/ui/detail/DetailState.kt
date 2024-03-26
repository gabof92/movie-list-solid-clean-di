package com.example.movies.ui.detail

import android.content.Context
import android.content.Intent
import android.view.View
import com.example.movies.ui.common.toHttpsUrl

class DetailState(
    private val context : Context
) {
    fun onTrailerClicked(view : View, trailer: String){
        if(trailer.isNotBlank()) {
            val youtubeLink: String = "https://www.youtube.com/watch?v=" + trailer
            val url = youtubeLink.toHttpsUrl()
            val intent = Intent(Intent.ACTION_VIEW, url)
            context.startActivity(intent)
        }
    }
}
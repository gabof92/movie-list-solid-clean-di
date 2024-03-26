package com.example.movies.ui.common

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.movies.App

fun String.toHttpsUrl() : Uri { return this.toUri().buildUpon().scheme("https").build() }

val Context.app : App get() = applicationContext as App
val Fragment.app : App get() = requireContext().app
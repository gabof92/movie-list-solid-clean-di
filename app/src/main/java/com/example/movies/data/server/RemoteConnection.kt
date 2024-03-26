package com.example.movies.data.server

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val API_KEY = "26f083bfabeb7d76eec1aac5a930e3c8"

const val BASE_URL_API =
    "https://api.themoviedb.org/3/"

const val BASE_URL_IMG =
    "https://image.tmdb.org/t/p/w92"

//Moshi object converts Json response to Kotlin object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//Creating Retrofit Object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_API)
    .build()

object RemoteConnection {
    val retrofitService: RemoteService by lazy {
        retrofit.create(RemoteService::class.java)
    }
}
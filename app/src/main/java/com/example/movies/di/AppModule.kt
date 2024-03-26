package com.example.movies.di

import android.app.Application
import androidx.room.Room
import com.example.data.datasource.MovieLocalDataSource
import com.example.data.datasource.MovieRemoteDataSource
import com.example.movies.R
import com.example.movies.data.database.AppDatabase
import com.example.movies.data.database.MovieRoomDataSource
import com.example.movies.data.server.MovieServerDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    //We name this dependency as there probably will be other String dependencies
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "app_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideRemoteDataSource(@ApiKey apiKey: String): MovieRemoteDataSource =
        MovieServerDataSource(apiKey)

    @Provides
    fun provideLocalDataSource(db: AppDatabase): MovieLocalDataSource =
        MovieRoomDataSource(db.movieDao())

}
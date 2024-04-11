package com.example.movies.di

import android.app.Application
import androidx.room.Room
import com.example.data.datasource.MovieLocalDataSource
import com.example.data.datasource.MovieRemoteDataSource
import com.example.movies.R
import com.example.movies.data.database.MovieDatabase
import com.example.movies.data.database.MovieRoomDataSource
import com.example.movies.data.server.MovieServerDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
        MovieDatabase::class.java,
        "app_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase) = db.movieDao()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{
    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSource: MovieServerDataSource): MovieRemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(localDataSource: MovieRoomDataSource): MovieLocalDataSource
}
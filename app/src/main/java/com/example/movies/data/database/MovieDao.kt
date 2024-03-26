package com.example.movies.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Transaction
    suspend fun insertWithoutWatched(entities: List<Movie>) {
        entities.forEach { movie ->
            val existingMovie = getById(movie.id)
            existingMovie?.let {
                movie.isWatched = it.isWatched
            }
            insert(listOf(movie))
        }
    }

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getById(id: Int): Movie

    @Update
    suspend fun update(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovie(id: Int): Flow<Movie>


    @Query("SELECT * FROM Movie")
    fun getAll(): Flow<List<Movie>>

}
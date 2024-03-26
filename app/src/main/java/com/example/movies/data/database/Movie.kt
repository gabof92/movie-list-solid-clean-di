package com.example.movies.data.database;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    var name: String,
    var image: String,
    var overview: String,
    @ColumnInfo(name = "user_score")
    var userScore: Float,
    var trailer: String,
    @ColumnInfo(name = "is_watched")
    var isWatched: Boolean = false,
)

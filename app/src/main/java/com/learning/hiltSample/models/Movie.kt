package com.learning.hiltSample.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    val backdrop_path: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val poster_path: String,
    val release_date: String,
    val title: String
)
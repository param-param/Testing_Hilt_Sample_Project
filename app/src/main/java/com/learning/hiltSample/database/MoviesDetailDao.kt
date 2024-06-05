package com.learning.hiltSample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.learning.hiltSample.models.Movie

@Dao
interface MoviesDao {

    @Insert
    suspend fun insertMovies(movies: List<Movie>)

    @Insert
    suspend fun insertMovie(movies: Movie)

    @Update
    suspend fun updateMovie(quote: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteMovie()

    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<Movie>

    @Query("SELECT * FROM movie where id = :movieId")
    suspend fun getMovieById(movieId: Int): Movie

}
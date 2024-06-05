package com.learning.hiltSample.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learning.hiltSample.models.Movie
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class MoviesDatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var moviesDatabase: MoviesDatabase

    private lateinit var moviesDao: MoviesDao

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        moviesDao = moviesDatabase.getMoviesDao()
    }

    @Test
    fun insertMovie_returnsSingleMovie() = runTest {
        val movie = Movie("", 1, "", "", "First Movie")
        moviesDao.insertMovies(listOf(movie))
        val result = moviesDao.getMovies()
        Assert.assertEquals(1, result.size)
    }


    @After
    fun closeDatabase() {
        moviesDatabase.close()
    }
}
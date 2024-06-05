package com.learning.hiltSample.api

import com.learning.hiltSample.Utility
import com.learning.hiltSample.utils.Constants
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.mockwebserver.MockWebServer


class MoviesServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var moviesService: MoviesService

    @Before
    fun setup(){
        mockWebServer = MockWebServer()
        moviesService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MoviesService::class.java)
    }

    @Test
    fun testGetMovies() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("{}")
        mockWebServer.enqueue(mockResponse)

        val response = moviesService.getUpcomingMoviesList(Constants.API_KEY, 1)
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.results.isNullOrEmpty())
    }

    @Test
    fun testGetMovies_returnMovies() = runTest{
        val mockResponse = MockResponse()
        val content = Utility.readFileResource("/sample_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = moviesService.getUpcomingMoviesList(Constants.API_KEY, 1)
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.results.isEmpty())
        Assert.assertEquals(1, response.body()!!.results.size)
    }

    @Test
    fun testGetProducts_returnError() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(404)
        mockResponse.setBody("Something went wrong")
        mockWebServer.enqueue(mockResponse)

        val response = moviesService.getUpcomingMoviesList(Constants.API_KEY, 1)
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.isSuccessful)
        Assert.assertEquals(404, response.code())
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }
}
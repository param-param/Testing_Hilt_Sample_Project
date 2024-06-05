package com.learning.hiltSample.api

import com.learning.hiltSample.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesList(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

}
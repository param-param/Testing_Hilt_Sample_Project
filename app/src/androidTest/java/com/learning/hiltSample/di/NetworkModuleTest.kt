package com.learning.hiltSample.di

import android.content.Context
import androidx.room.Room
import com.learning.hiltSample.api.MoviesService
import com.learning.hiltSample.database.MoviesDatabase
import com.learning.hiltSample.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
@Module
class NetworkModuleTest {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

}